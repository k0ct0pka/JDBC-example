package configure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionsPoolConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public HikariConfig config(){
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(10);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_example_db");
        config.setUsername("root");
        config.setPassword("");

        return config;
    }
}
