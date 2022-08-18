<%-- 
    Document   : register
    Created on : Aug 11, 2022, 4:54:57 PM
    Author     : Sky
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Registration Page</h1>
        
        <h4>Menu</h4>
        <ul>
            <li> <a href ="login">login</a></li>
        </ul>
        <div>
            <h2>Registration</h2>
            <form method="POST" action="register">
                <label>Email:</label>
                <input type="text" name="r_email" value="">
                <br>
                <label>Password:</label>
                <input type="text" name ="r_password" value ="">
                <br>
                <label>First Name:</label>
                <input type="text" name ="r_first" value ="">
                <br>
                <label>Last Name:</label>
                <input type="text" name ="r_last" value ="">
                <br>
<!--                 <label>Role:</label>
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                        <option value ="${role.roleId}">${role.roleName}</option>
                    </c:forEach>
                </select>-->
                    <br>
                <input type="submit" value="add">
                <input type="hidden" name="action" value ="add">

            </form>

        </div>
        
        
    </body>
</html>
