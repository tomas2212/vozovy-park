/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.view;

import cz.muni.fi.pa165.ejb.DTO.CarDTO;
import cz.muni.fi.pa165.ejb.sevices.CarService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tomas
 */
@WebServlet(name = "CarServlet", urlPatterns = {"/Car"})
public class Car extends HttpServlet {
    
    @EJB
    private CarService carService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       CarDTO car = new CarDTO(); 
       car.setModel("fddf");
       
       car = carService.createCar(car);
       PrintWriter out = resp.getWriter();
       out.println(car.getId().toString());
       
    }
    
}
