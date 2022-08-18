package servlets;

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

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        //loading JPS
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
   AccountService as = new AccountService();

        // Login Parameters
        String email = request.getParameter("e_email");
        String password = request.getParameter("p_password");


        // Providing error message for empty information
        if (email == null || email.equals("") || password == null || password.equals("") ) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);

            //setting return message
            String Message = "Enter a username and password";
            request.setAttribute("message", Message);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        
        
        
        User user = as.login(email, password);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);

            int roleId = user.getRole().getRoleId();

            if (roleId == 1 || roleId == 3) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("inventory");
            }

    
            
            
            
        }

    }

}
