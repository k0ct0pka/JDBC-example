package servlets;

import dto.Car;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.LoginService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
@WebServlet("/index")
public class HomePageServlet  extends HttpServlet {
    private CarService carService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        carService = (CarService) servletContext.getAttribute("carService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = carService.getAllCars();
        Collections.shuffle(cars);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher(req.getContextPath()+"/jsp/index.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = carService.getCarsBySearch(req);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher(req.getContextPath()+"/jsp/index.jsp").forward(req, resp);
    }
}
