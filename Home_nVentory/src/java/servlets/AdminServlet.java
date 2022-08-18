package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Role;
import models.User;
import services.AccountService;
import services.CategoriesService;
import services.InventoryService;
import services.RoleService;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //loading http session
        HttpSession session = request.getSession();

        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        CategoriesService cs = new CategoriesService();
        RoleService rs = new RoleService();

        String action = request.getParameter("action");

        try {
            List<User> users = as.getAll();
            request.setAttribute("users", users);
        } catch (Exception e) {
            System.out.println("error e");
            session.setAttribute("message", e);
        }

        try {
            List<Category> categoryList = cs.getAll();
            request.setAttribute("categorylist", categoryList);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            List<Role> role = rs.getAll();
            request.setAttribute("roles",role);
            
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        if (!session.getAttribute("email").toString().equals("admin")) {
//            response.sendRedirect("login");
//            return;
//        }
        //creating inventory movement
        String Inventory = request.getParameter("inventory");
        if (Inventory != null) {
            response.sendRedirect("inventory");
        }

        // creating Logout 
        String Logout = request.getParameter("logout");

        if (Logout != null) {
            //invalidates the session
            session.invalidate();
            //logout message
            String message = "You have successfully logged out.";
            request.setAttribute("message", message);
            //load login JSP
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
   
        try {
            User euser = null;
         String editUser = request.getParameter("edit_email");

            euser = as.get(editUser);
            request.setAttribute("euser", euser);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        //Load the JSP
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String emailSession = (String) session.getAttribute("email");

        
        //grabbing values from the add form
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String roleid = request.getParameter("role");
        boolean active = true;


        //creating account service
        AccountService as = new AccountService();
        CategoriesService cs = new CategoriesService();

        // get all the users
        try {
            List<User> users = as.getAll();
            request.setAttribute("users", users);
        } catch (Exception e) {
            System.out.println("error e");
            session.setAttribute("message", e);
        }
        
   User activeuser;
        
        // switch statement to add/ update/ delete users
        try {
            switch (action) {
                case "add":
                    as.insert(email, active, firstName, lastName, password, Integer.parseInt(roleid));
                    break;
                case "edit":
                    as.update(email, firstName, lastName, password, active);
                    break;
                    case"toggling":
                        UserDB userdb = new UserDB();
                        activeuser = as.get(email);
                        if ( activeuser.getActive() == true) {
                            activeuser.setActive(false);
                            userdb.update(activeuser);

                        }else {
                            activeuser.setActive(true);
                            userdb.update(activeuser);
                        }
                    break;
                case "delete":
                    as.delete(email);
                    break;
            }
        } catch (Exception e) {
        }
         response.sendRedirect("admin");
        
        }

    }
