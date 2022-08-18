
package filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest =(HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String email = (String) session.getAttribute("email");
        User user = null;
        AccountService as = new AccountService();
        
     
        try {
            user = as.get(email);
            
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int roleid = user.getRole().getRoleId();
        
        if(  roleid == 2){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
           httpResponse.sendRedirect("inventory");
           return;
        }
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }
    @Override
    public void destroy() {
        
    }
  
}
