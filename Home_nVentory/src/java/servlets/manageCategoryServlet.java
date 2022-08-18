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
import models.Category;
import services.CategoriesService;

/**
 *
 * @author Sky
 */
public class manageCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriesService cs = new CategoriesService();

        try {
            List<Category> categoryList = cs.getAll();
            request.setAttribute("categorylist", categoryList);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Category categoryid = null;
        int CATEID;

        String categoryID = request.getParameter("cateid");

        try {

            CATEID = Integer.parseInt(categoryID);
            categoryid = cs.get(CATEID);

            request.setAttribute("cateID", categoryid);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriesService cs = new CategoriesService();

        //category portion 
        String action_cate = request.getParameter("action_cate");
        String categoryName = request.getParameter("category_name");

        // Portion for adding and updating Category
        try {

            switch (action_cate) {
                case "add":
                    cs.insert(categoryName);
                case "update":
//                    cs.update(categoryId, categoryName);
                    break;
            }
        } catch (Exception e) {

        }

        response.sendRedirect("category");
    }

}
