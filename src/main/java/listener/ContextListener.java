package listener;

import com.zaxxer.hikari.pool.HikariPool;
import configure.ConnectionsPoolConfig;
import dao.UserDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import util.ConfigReader;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigReader reader = new ConfigReader();
        ConnectionsPoolConfig connectionsPoolConfig = reader.readDataBaseConfig();
        HikariPool hikariPool = new HikariPool(connectionsPoolConfig.config());
        UserDao userDao = new UserDao(hikariPool);
    }
}
