package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import service.RegistrationService;

import java.io.IOException;
@AllArgsConstructor
@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {
    private RegistrationService registrationService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        registrationService = (RegistrationService) servletContext.getAttribute("registrationService");
    }
    public AuthenticationServlet(){
        super();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(registrationService.verifyUser(req)){
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            resp.sendRedirect(req.getContextPath() + "/registration");
        }
    }
}
