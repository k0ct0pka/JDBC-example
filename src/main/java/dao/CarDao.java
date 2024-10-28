//package dao;
//
//import com.zaxxer.hikari.pool.HikariPool;
//import dto.Car;
//import dto.Credentials;
//import dto.User;
//
//import java.sql.*;
//import java.util.*;
//
//public class CarDao {
//    private HikariPool hikariPool;
//    public List<Car> findAll(){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(Statement stmt = conn.createStatement()) {
//                ResultSet rs = stmt.executeQuery("SELECT u.id,u.name,u.email,u.password , c.id , c.mark , c.model , c.year , c.price, c.image , c.user_id FROM users u inner join cars c on u.id = c.user_id");
//                Map<User,List<Car>> cars = new HashMap<>();
//                int currentId=0;
//                while(rs.next()) {
//                    currentId = rs.getInt("u.id");
//                    while()
//                    rs.absolute(rs.getRow()-1);
//                }
//                rs.close();
//                return users;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public List<Car> findByUserId(Integer userId){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(PreparedStatement stmt = conn.prepareStatement("SELECT c.id , c.mark , c.model , c.year , c.price, c.image , c.user_id FROM cars c where c.user_id = ?")) {
//                stmt.setInt(1, userId);
//                ResultSet rs = stmt.executeQuery();
//                List<Car> cars = new ArrayList<>();
//                while (rs.next()) {
//                    cars.add(buildCar(rs));
//                }
//                rs.close();
//                return cars;
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public Optional<User> findByEmail(String email){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id,u.name,u.email,u.password , c.id , c.mark , c.model , c.year , c.price, c.image , c.user_id FROM users u inner join cars c on u.id = c.user_id where u.email = ?")) {
//                stmt.setString(1, email);
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
//    public boolean update(User user){
//        try(Connection conn = hikariPool.getConnection()) {
//            try(PreparedStatement stmt = conn.prepareStatement("UPDATE users u SET u.name = ?, u.email = ?, u.password = ? WHERE id = ?")) {
//                stmt.setString(1, user.getName());
//                stmt.setString(2, user.getCredentials().getEmail());
//                stmt.setString(3, user.getCredentials().getPassword());
//                stmt.setInt(4, user.getId());
//                return stmt.execute();
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
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
//    private Car buildCar(ResultSet rs) throws SQLException {
//        return Car.builder()
//                        .id(rs.getInt("c.id"))
//                        .mark(rs.getString("c.mark"))
//                        .model(rs.getString("c.model"))
//                        .year(rs.getInt("c.year"))
//                        .price(rs.getDouble("c.price"))
//                        .image(rs.getBlob("c.image"))
//                        .build();
//    }
//}
