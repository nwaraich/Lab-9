
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;


public class InventoryService {
    
     public List<Item> getAll() throws Exception {
        ItemsDB itemdb = new ItemsDB();
        List<Item> items = itemdb.getAll();
        return items;
    }
    
    public void insert( int categoryID ,String itemName, double price , String owner) throws Exception {
        Item item = new Item(0, itemName, price);
        UserDB userdb = new UserDB();
        User user = userdb.get(owner);
        
        item.setOwner(user);
        
        CategoriesDB categorydb = new CategoriesDB();
        Category category = categorydb.get(categoryID);
        
     item.setCategory(category);
       
        ItemsDB itemdb = new ItemsDB();

        itemdb.insert(item);
        
    }
    
    public Item get(Integer itemId) throws Exception {
        ItemsDB itemdb = new ItemsDB();
        return itemdb.get(itemId);     
    }
    
    public void delete( int itemID) throws Exception {
        ItemsDB itemdb = new ItemsDB();
        Item item = itemdb.get(itemID);
        itemdb.delete(item);
        
    }
    
    public void update(Integer itemId, int categoryID, String itemName, double price) throws Exception {
        ItemsDB itemdb = new ItemsDB();
        
        Item item = itemdb.get(itemId);
        
//        CategoriesDB categorydb = new CategoriesDB();
//        Category category = categorydb.get(categoryID);
//        
//        item.setCategory(category);
        item.setItemName(itemName);
        item.setPrice(price);
        itemdb.update(item);    
    }
}
