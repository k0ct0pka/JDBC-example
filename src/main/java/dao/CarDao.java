package dao;

import com.zaxxer.hikari.pool.HikariPool;
import dto.Car;
import dto.Credentials;
import dto.User;
import lombok.AllArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
@AllArgsConstructor
public class CarDao {
    private HikariPool hikariPool;
    public List<Car> findAll(){
        try(Connection conn = hikariPool.getConnection()) {
            try(Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT u.id,u.name,u.email,u.password , c.id , c.mark , c.model , c.year , c.price, c.image , c.user_id FROM cars c left join users u on u.id = c.user_id");
                List<Car> cars = new ArrayList<>();
                while(rs.next()) {
                    cars.add(buildCar(rs));

                }
                rs.close();
                return cars;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Car> findByParam(String param){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id,u.name,u.email,u.password , c.id , c.mark , c.model , c.year , c.price, c.image , c.user_id FROM cars c left join users u on c.user_id = u.id where c.model like ? OR c.mark like ? OR u.name like ?")) {
                stmt.setString(1, "%"+param+"%");
                stmt.setString(2, "%"+param+"%");
                stmt.setString(3, "%"+param+"%");
                ResultSet rs = stmt.executeQuery();
                List<Car> cars = new ArrayList<>();
                while (rs.next()) {
                    cars.add(buildCar(rs));
                }
                rs.close();
                return cars;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Car> findByUserId(Integer userId){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id,u.name,u.email,u.password , c.id , c.mark , c.model , c.year , c.price, c.image , c.user_id FROM users u inner join cars c on u.id = c.user_id where c.user_id = ?")) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                List<Car> cars = new ArrayList<>();
                while (rs.next()) {
                    cars.add(buildCar(rs));
                }
                rs.close();
                return cars;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public boolean save(User user){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, password) values (?, ?, ?)")) {
//                stmt.setString(1, user.getName());
//                stmt.setString(2, user.getCredentials().getEmail());
//                stmt.setString(3, user.getCredentials().getPassword());
//                return stmt.execute();
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public boolean update(Car car){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("UPDATE cars c SET c.mark = ?, c.model = ?, c.price=?, c.year=? WHERE c.id = ?")) {
                stmt.setString(1, car.getMark());
                stmt.setString(2, car.getModel());
                stmt.setBigDecimal(3, BigDecimal.valueOf(car.getPrice()));
                stmt.setInt(4, car.getYear());
                stmt.setInt(5, car.getId());
                return stmt.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public boolean delete(Integer id){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(PreparedStatement stmt = conn.prepareStatement("Delete from users where id = ?")) {
//                stmt.setInt(1, id);
//                return stmt.execute();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private User buildCarWithUser(ResultSet rs) throws SQLException {
//        return User.builder()
//                .id(rs.getInt("u.id"))
//                .name(rs.getString("u.name"))
//                .credentials(Credentials.builder()
//                        .email(rs.getString("u.email"))
//                        .password(rs.getString("u.password"))
//                        .build())
//                .cars(List.of(Car.builder()
//                        .id(rs.getInt("c.id"))
//                        .mark(rs.getString("c.mark"))
//                        .model(rs.getString("c.model"))
//                        .year(rs.getInt("c.year"))
//                        .price(rs.getDouble("c.price"))
//                        .image(rs.getBlob("c.image"))
//                        .build()))
//                .build();
//    }
    private Car buildCar(ResultSet rs) throws SQLException {
        User user = User.builder().id(rs.getInt("u.id"))
                .name(rs.getString("u.name"))
                .credentials(Credentials.builder()
                .email(rs.getString("u.email"))
                .password(rs.getString("u.password"))
                .build()).build();
        String encoded = Base64.getEncoder().encodeToString(rs.getBlob("c.image").getBytes(1L, (int) rs.getBlob("c.image").length()));
        return Car.builder()
                        .id(rs.getInt("c.id"))
                        .mark(rs.getString("c.mark"))
                        .model(rs.getString("c.model"))
                        .year(rs.getInt("c.year"))
                        .price(rs.getDouble("c.price"))
                        .image(encoded)
                        .user(user)
                        .build();
    }
}
