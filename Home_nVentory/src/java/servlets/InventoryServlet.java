package servlets;

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
import models.Item;
import models.User;
import services.AccountService;
import services.CategoriesService;
import services.InventoryService;

public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //creates session object
        HttpSession session = request.getSession();
        //creating user object 
        User user = null;
        // loading session username
        String email = (String) session.getAttribute("email");

        // all services that will get used
        CategoriesService cs = new CategoriesService();
        InventoryService is = new InventoryService();
        AccountService as = new AccountService();

        try {
            Item item = null;
            String Item = request.getParameter("ItemEdit");

            item = is.get(Integer.parseInt(Item));
            request.setAttribute("item_id", item);

        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //grabs logout parameter
        String Logout = request.getParameter("logout");
        // adding menu movement parameters
        String Inventory = request.getParameter("inventory");

        if (Inventory != null) {
            response.sendRedirect("inventory");
        }

        //making sure that an active username is used to move on
        if (session.getAttribute("email").toString() == null) {
            response.sendRedirect("login");
            return;
        }

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

        //Get the item list
        try {
            user = as.get(email);
            List<Item> itemList = user.getItemList();
            request.setAttribute("itemlist", itemList);
        } catch (Exception e) {
            System.out.println("error e");
            session.setAttribute("message", e);
        }

        // gets all the categories
        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            System.out.println("error e");
            session.setAttribute("message", e);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // getting session object
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        User user = null;

        AccountService as = new AccountService();
        CategoriesService cs = new CategoriesService();
        InventoryService is = new InventoryService();

        //Inventory actions are three
        String action = request.getParameter("action");
        String itemid = request.getParameter("ItemID");
        String category = request.getParameter("category");
        String itemName = request.getParameter("item_name");
        String price = request.getParameter("price");

        //            double Price = Double.parseDouble(price);
//            int Category = Integer.parseInt(category);
//            int itemID = Integer.parseInt(itemid);
        try {

            switch (action) {
                case "add":
                    is.insert(Integer.parseInt(category), itemName, Double.parseDouble(price), email);
                    break;
                case "update":
                    is.update(Integer.parseInt(itemid), Integer.parseInt(category), itemName, Double.parseDouble(price));
                    System.out.println("This was clicked");
                case "delete":
                    is.delete(Integer.parseInt(itemid));
                default:
                    break;
            }
        } catch (Exception E) {

        }

        try {
            user = as.get(email);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            user = as.get(email);
            List<Item> itemList = user.getItemList();
            request.setAttribute("itemlist", itemList);
        } catch (Exception e) {
            System.out.println("error e");
            session.setAttribute("message", e);
        }

        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            System.out.println("error e");
            session.setAttribute("message", e);
        }

        response.sendRedirect("inventory");
    }

}
