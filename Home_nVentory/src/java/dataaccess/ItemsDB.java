/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.User;

/**
 *
 * @author Sky
 */
public class ItemsDB {
    
    public List<Item> getAll() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
    
         try {
           List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
           return items;
       } finally {
           em.close();
       }
    }
  
   public Item get(Integer itemID) throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       
       try {
           Item items = em.find(Item.class, itemID);
           return items;
       } finally {
           em.close();
       }
       
   } 
            
   public void insert(Item item) throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       
       try {
           User user = item.getOwner();
           user.getItemList().add(item);
           trans.begin();
           em.persist(item);
           em.merge(user);
           trans.commit();
       } catch (Exception e) {
           trans.rollback();
       } finally{
           em.close();
       }
       
   }
   
   
      public void delete(Item item) throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       
       try {
           User user = item.getOwner();
           user.getItemList().remove(item);
           trans.begin();
           em.remove(em.merge(item));
           em.merge(user);
           trans.commit();
       } catch (Exception e) {
           trans.rollback();
       } finally{
           em.close();
       }
       
   }
   
      public void update(Item item) throws Exception {
          EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally{
            em.close();
        }
      }
      
      
}
