package listener;

import com.zaxxer.hikari.pool.HikariPool;
import configure.ConnectionsPoolConfig;
import configure.EmailSenderConfig;
import dao.CarDao;
import dao.UserDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.*;
import util.CodeGenerator;
import util.ConfigReader;

import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigReader reader = new ConfigReader();
        ConnectionsPoolConfig connectionsPoolConfig = reader.readDataBaseConfig();
        HikariPool hikariPool = new HikariPool(connectionsPoolConfig.config());
        UserDao userDao = new UserDao(hikariPool);
        CarDao carDao = new CarDao(hikariPool);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        CodeGenerator codeGenerator = new CodeGenerator();
        CredentialsService credentialsService = new CredentialsService();
        EmailSenderConfig emailSenderConfig = new EmailSenderConfig();
        EmailSenderService emailSenderService = new EmailSenderService(emailSenderConfig.mailSenderConfig());
        RegistrationService registrationService = new RegistrationService(userDao,bCryptPasswordEncoder,codeGenerator,emailSenderService,credentialsService);
        LoginService loginService = new LoginService(credentialsService,userDao,bCryptPasswordEncoder);
        CarService carService = new CarService(carDao);
        Map<String, Object> params = new HashMap<>();
        params.put("registrationService",registrationService);
        params.put("loginService",loginService);
        params.put("carService",carService);
        putToContext(sce, params);
    }
    private void putToContext(ServletContextEvent sce, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sce.getServletContext().setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
