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
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return config;
    }
}
