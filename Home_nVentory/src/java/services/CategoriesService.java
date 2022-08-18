/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import java.util.List;
import models.Category;
import models.User;

/**
 *
 * @author Navkaran Waraich
 */
public class CategoriesService {
    
     public List<Category> getAll() throws Exception {
        CategoriesDB categoriesdb = new CategoriesDB();
        List<Category> categories = categoriesdb.getAll();
        return categories;
    }
     
     public Category get(Integer CategoryId) throws Exception{
    CategoriesDB categoriesdb  = new CategoriesDB();
   return categoriesdb.get(CategoryId);

    }
     
     public void update(Integer categoryId, String categoryName) throws Exception {
         CategoriesDB categorydb = new CategoriesDB();
         
         Category category = categorydb.get(categoryId);
         
         category.setCategoryName(categoryName);
         categorydb.update(category);
         
     }
    
     public void insert(String categoryName) throws Exception {
         Category category = new Category(0,categoryName);
         CategoriesDB categorydb = new CategoriesDB();
         
         categorydb.insert(category);

     }
     
}
