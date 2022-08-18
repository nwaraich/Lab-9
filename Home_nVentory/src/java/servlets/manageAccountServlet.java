/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author Sky
 */
public class manageAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService as = new AccountService();
        //creates session object
        HttpSession session = request.getSession();
        //creating user object 
        User user = null;
        // loading session username
        String email = (String) session.getAttribute("email");

        try {
            user = as.get(email);
            request.setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountService as = new AccountService();

        String action = request.getParameter("action");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        boolean active = true;

        //creating user object 
        User user = null;
        // loading session username
        String email = (String) session.getAttribute("email");

        try {
            switch (action) {
                case "edit": {
                    as.update(email, firstName, lastName, password, active);
                    String message = "account";
                    request.setAttribute("message", message);
                    response.sendRedirect("account");

                    break;
                }
                case "toggling": {
                    UserDB userdb = new UserDB();
                    user = as.get(email);
                    user.getActive();
                    user.setActive(false);
                    userdb.update(user);
                    session.invalidate();
                    response.sendRedirect("login");
                    String falseMessage = "account deactivated";
                    request.setAttribute("falseMessage", falseMessage);
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(manageAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
