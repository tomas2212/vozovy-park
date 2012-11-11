/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.view;

import cz.muni.fi.pa165.ejb.DTO.CarDTO;
import cz.muni.fi.pa165.ejb.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.ejb.sevices.CarService;
import cz.muni.fi.pa165.ejb.sevices.CompanyLevelService;
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
@WebServlet(name = "CompanyLevelServlet", urlPatterns = {"/CompanyLevel"})
public class CompanyLevel extends HttpServlet {
    
    @EJB
    private CompanyLevelService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
       
       
       CompanyLevelDTO cl  = service.createCompanyLevel("CEO");
       PrintWriter out = resp.getWriter();
       out.println(cl.getId().toString());
       
    }
    
}
