package dao;

import com.zaxxer.hikari.pool.HikariPool;
import dto.Credentials;
import dto.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class UserDao {
    private HikariPool hikariPool;
    public List<User> findAll(){
        try(Connection conn = hikariPool.getConnection()) {
            try(Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT u.id , u.name, u.email , u.password FROM user u");
                List<User> users = new ArrayList<>();
                while(rs.next()) {
                    users.add(buildUser(rs));
                }
                rs.close();
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<User> findById(Integer id){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id , u.name, u.email , u.password FROM user u where u.id = ?")) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(!rs.next())return Optional.empty();
                Optional<User> user = Optional.of(buildUser(rs));
                rs.close();
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<User> findByEmail(String email){
        try(Connection conn = hikariPool.getConnection()) {
            try(PreparedStatement stmt = conn.prepareStatement("SELECT u.id , u.name, u.email , u.password FROM user u where u.email = ?")) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if(!rs.next())return Optional.empty();
                Optional<User> user = Optional.of(buildUser(rs));
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
        return User.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .credentials(Credentials.builder()
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build())
                .build();
    }
}
