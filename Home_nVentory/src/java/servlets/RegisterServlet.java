/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import services.AccountService;
import services.RoleService;

/**
 *
 * @author Sky
 */
public class RegisterServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        RoleService rs = new RoleService();
        
        try {
            List<Role> role = rs.getAll();
            request.setAttribute("roles",role);
            
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        return;
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("r_email");
        String action = request.getParameter("action");
        String password = request.getParameter("r_password");
        String firstName = request.getParameter("r_first");
        String lastName = request.getParameter("r_last");
        String role = "2";
        boolean active = true;
        
        AccountService as = new AccountService();
        
        try {
            as.insert(email, active, firstName, lastName, password, Integer.parseInt(role));
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        response.sendRedirect("register");
    }


}
