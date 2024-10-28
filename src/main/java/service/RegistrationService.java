package service;

import dao.UserDao;
import dto.Credentials;
import dto.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.CodeGenerator;

@AllArgsConstructor
public class RegistrationService {
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CodeGenerator codeGenerator;
    private EmailSenderService emailSenderService;
    private CredentialsService credentialsService;
    public void registerUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        Credentials credentials = credentialsService.extract(req);
        if(userDao.findByEmail(credentials.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        credentials.setPassword(bCryptPasswordEncoder.encode(credentials.getPassword()));
        User user = User.builder()
                .name(name)
                .credentials(credentials)
                .build();
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        sendEmail(credentials.getEmail(),session);

    }
    private void sendEmail(String email , HttpSession session) {
        String code = codeGenerator.generateCode();
        emailSenderService.sendEmail(email,"Your Code",code);
        session.setAttribute("code", code);
    }
    public boolean verifyUser(HttpServletRequest req) {
        String codeProvided = req.getParameter("code");
        HttpSession session = req.getSession();
        String codeGenerated =(String) session.getAttribute("code");
        if(codeProvided.equals(codeGenerated)){
            User user = (User) session.getAttribute("user");
            userDao.save(user);
            session.removeAttribute("user");
            return true;
        } else {
            session.invalidate();
            return false;
        }
    }
}
