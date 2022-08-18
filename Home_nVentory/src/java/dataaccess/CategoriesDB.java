
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;


public class CategoriesDB {
    
    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
     
        try {
            List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
        return categories;
        } finally {
            em.close();
        }
    }
    
    public Category get(Integer categoryId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Category categories = em.find(Category.class, categoryId);
            return categories;
        } finally {
            em.close();
        }
    }
    
    public void update(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(category);
            trans.commit();  
        }catch(Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void insert(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
          try {   
           trans.begin();
           em.merge(category);
           trans.commit();
       } catch (Exception e) {
           trans.rollback();
       } finally{
           em.close();
       }
        
    }
    
    
}
