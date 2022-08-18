<%-- 
    Document   : account
    Created on : Aug 13, 2022, 5:17:34 PM
    Author     : Sky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account </title>
    </head>
    <body>
        <h1>Edit Account for ${user.firstName}</h1>
        <h4>Menu</h4>
        <ul>
            <li> <a href ="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href ="account">Manage Account</a></li>
            <li> <a href="login?logout">Logout</a></li>
        </ul>
        
         <div>
                    <form method ="POST" action="account">
                        <h2>Edit user</h2>
                        <label>Email:</label>
                        <input type="text" name ="email" value ="${user.email}" >
                        <br>
                        <label>First Name:</label>
                        <input type="text" name ="firstName" value ="${user.firstName}" >
                        <br>
                        <label>Last Name:</label>
                        <input type="text" name="lastName" value="${user.lastName}" >
                        <br> 
                        <label>Password:</label>
                        <input type="text" name ="password" value="${user.password}">
                        <br>
                        <input type="submit" value="edit">
                        <input type="hidden" name ="action" value ="edit" >
                    </form>
                        <label>Account is currently: ${user.active}</label>
                        <form method="POST" action="account" >
                            <input type="submit" value="toggling" >
                            <input type="hidden" name ="action" value="toggling">
                            <input type="hidden" name="email" value="${user.email}" >
                           </form>
                           <p>${message}</p>
                           <p>${falseMessage}</p>
                </div>
    </body>
</html>
