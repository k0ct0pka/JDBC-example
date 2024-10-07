package service;

import dto.Credentials;
import dto.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class LoginService {
    private CredentialsService credentialsService;
    public User login(HttpServletRequest request) {
        Credentials credentials = credentialsService.extract(request);

    }
}
