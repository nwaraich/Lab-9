package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB {
    
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public void insert(User users) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(users);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally{
            em.close();
        }
        
    }
    
    public void update(User users) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(users);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally{
            em.close();
        }

    }
   
    public void delete( User users ) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.remove( em.merge(users));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally{
            em.close();
        }
    }
    
}
    
    
    
    
    

