package service;

import dao.UserDao;
import dto.Credentials;
import dto.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
public class RegistrationService {
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void registerUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = User.builder()
                .name(name)
                .credentials(Credentials.builder()
                        .email(email)
                        .password(bCryptPasswordEncoder.encode(password))
                        .build())
                .build();
        if(!userDao.save(user)){
            throw new RuntimeException("User not registered!");
        }

    }
    private void sendEmail(String email) {

    }
}
