/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.view;

import cz.muni.fi.pa165.ejb.DTO.EmployeeDTO;
import cz.muni.fi.pa165.ejb.sevices.EmployeeService;
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
 * @author Lukas Maticky
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = {"/Employee"})
public class Employee extends HttpServlet {

    @EJB
    private EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setName("Lukas");

        employee = employeeService.createEmployee(employee);
        PrintWriter out = resp.getWriter();
        out.println(employee.getId().toString());

    }
}
