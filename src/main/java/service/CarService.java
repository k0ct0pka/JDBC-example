package service;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.Blob;
import dao.CarDao;
import dto.Car;
import dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@AllArgsConstructor
public class CarService {
    private JakartaServletDiskFileUpload upload;
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
    public boolean add(HttpServletRequest req) throws ServletException, IOException {
        List<DiskFileItem> items = upload.parseRequest(req);
        InputStream image = items.get(1).getInputStream();
        Car car = Car.builder()
                .mark(items.get(2).getString())
                .model(items.get(3).getString())
                .year(Integer.parseInt(items.get(4).getString()))
                .price(Double.parseDouble(items.get(5).getString()))
                .build();

        return carDao.save(car,image,((User)req.getSession().getAttribute("user")).getId());
    }
    public boolean delete(HttpServletRequest req) throws ServletException, IOException {
        Integer id =Integer.parseInt(req.getParameter("carId"));
        return carDao.delete(id);
    }
}
