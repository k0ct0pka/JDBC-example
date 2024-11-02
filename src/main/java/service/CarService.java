package service;

import dao.CarDao;
import dto.Car;
import dto.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class CarService {
    private CarDao carDao;
    public List<Car> getAllCars() {
        return carDao.findAll();
    }
    public List<Car> getCarsBySearch(HttpServletRequest req){
        String search = req.getParameter("search");
        return carDao.findByParam(search);
    }
    public List<Car> getUserCars(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("user");
        if(user == null){throw new RuntimeException("User not found");}
        return carDao.findByUserId(user.getId());
    }
    public boolean update(HttpServletRequest req){
        Car car = Car.builder()
                .mark((String) req.getParameter("mark"))
                .model((String) req.getParameter("model"))
                .year(Integer.parseInt(req.getParameter("year")))
                .price(Double.parseDouble(req.getParameter("price")))
                .id(Integer.parseInt(req.getParameter("carId")))
                .build();
        return carDao.update(car);
    }
}
