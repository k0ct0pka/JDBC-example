package service;

import dao.UserDao;
import dto.Credentials;
import dto.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@AllArgsConstructor

public class LoginService {
    private CredentialsService credentialsService;
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public User login(HttpServletRequest request) {
        Credentials credentials = credentialsService.extract(request);
        Optional<User> userByEmail = userDao.findByEmail(credentials.getEmail());
        HttpSession session = request.getSession();
        if (!userByEmail.isPresent()) {
            return null;
        } else if (bCryptPasswordEncoder.matches(credentials.getPassword(), userByEmail.get().getCredentials().getPassword())) {
            session.setAttribute("user", userByEmail.get());
            return userByEmail.get();
        }
        return null;
    }
}
