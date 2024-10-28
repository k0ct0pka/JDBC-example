package dao;

import com.zaxxer.hikari.pool.HikariPool;
import dto.Car;
import dto.Credentials;
import dto.User;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.*;

@AllArgsConstructor
public class UserDao {
    private HikariPool hikariPool;
//    private CarDao carDao;
    public List<User> findAllWithCars(){
        try(Connection conn = hikariPool.getConnection()) {
            try(Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT u.id , u.name , u.password , u.email , c.id , c.mark , c.model , c.year , c.price , c.image , c.user_id FROM users u join cars c on u.id = c.user_id order by u.email");
                Map<Integer,User> users = new HashMap<>();
                while(rs.next()) {
                    User user = buildUser(rs);
                    if(users.containsKey(user.getId())) {
                        users.get(user.getId()).getCars().add(user.getCars().get(0));
                    } else{
                        users.put(user.getId(), user);
                    }
                }
                rs.close();
                return Collections.unmodifiableList((List<? extends User>) users.values());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public Optional<User> findById(Integer id){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id , u.name, u.email , u.password FROM users u where u.id = ?")) {
//                stmt.setInt(1, id);
//                ResultSet rs = stmt.executeQuery();
//                if(!rs.next())return Optional.empty();
//                Optional<User> user = Optional.of(buildUser(rs));
//                rs.close();
//                return user;
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public Optional<User> findByEmail(String email){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id , u.name , u.password , u.email, c.id , c.mark , c.model , c.year , c.price , c.image , c.user_id FROM users u left join cars c on u.id = c.user_id where u.email = ?")) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if(!rs.next())return Optional.empty();
                Optional<User> user = Optional.of(buildUser(rs));
                while(rs.next()) {
                    User next = buildUser(rs);
                    user.get().getCars().add(next.getCars().get(0));
                }
                rs.close();
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean save(User user){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, password) values (?, ?, ?)")) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getCredentials().getEmail());
                stmt.setString(3, user.getCredentials().getPassword());
                return stmt.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean update(User user){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("UPDATE users u SET u.name = ?, u.email = ?, u.password = ? WHERE id = ?")) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getCredentials().getEmail());
                stmt.setString(3, user.getCredentials().getPassword());
                stmt.setInt(4, user.getId());
                return stmt.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean delete(Integer id){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("Delete from users where id = ?")) {
                stmt.setInt(1, id);
                return stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private User buildUser(ResultSet rs) throws SQLException {
        List<Car> car = new ArrayList<>();
        car.add(Car.builder()
                .id(rs.getInt("c.id"))
                .mark(rs.getString("c.mark"))
                .model(rs.getString("c.model"))
                .year(rs.getInt("c.year"))
                .price(rs.getDouble("c.price"))
                .image(rs.getBlob("c.image"))
                .build());
        return User.builder()
                .id(rs.getInt("u.id"))
                .name(rs.getString("u.name"))
                .credentials(Credentials.builder()
                        .email(rs.getString("u.email"))
                        .password(rs.getString("u.password"))
                        .build())
                .cars(car)
                .build();
    }
}
