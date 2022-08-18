

package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Sky
 */
public class AccountService {
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public void insert(String email, boolean active, String firstName, String lastName, String password,int roleId) throws Exception {
        User user = new User( email, active, firstName, lastName, password);
        UserDB userdb = new UserDB();
        
        RoleDB roledb = new RoleDB();
        Role role = roledb.get(roleId);
        user.setRole(role);
        
        userdb.insert(user);
    }
    
    public User get(String email) throws Exception{
    UserDB userdb = new UserDB();
   return userdb.get(email);
    
    }

    public User login (String email, String password){
        UserDB userdb = new UserDB();
        
        try {
            User users = userdb.get(email);
            if ( password.equals(users.getPassword())){
                return users;
            }
            
        } catch (Exception e) {
        }
        return null;
    }
    
   public void update(String email, String firstName, String lastName, String password, Boolean active) throws Exception {
    UserDB userdb = new UserDB();
    
    User users = userdb.get(email);

    users.setFirstName(firstName);
    users.setLastName(lastName);
    users.setPassword(password);
    users.setActive(active);
    userdb.update(users);
    }
   
   public void delete ( String email) throws Exception {
       UserDB userdb = new UserDB();
       User user = userdb.get(email);
       userdb.delete(user);
       
   }
   
   
}

