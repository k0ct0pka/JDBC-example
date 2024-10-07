package service;

import dto.Credentials;
import jakarta.servlet.http.HttpServletRequest;

public class CredentialsService {
    public Credentials extract(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || password == null) {
            throw new RuntimeException("Email and password are required");
        }
        return Credentials.builder()
                .email(email)
                .password(password)
                .build();
    }
}
