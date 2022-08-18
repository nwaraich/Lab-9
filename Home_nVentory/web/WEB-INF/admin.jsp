

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home nVentory</title>
    </head>
    <body>
        <h1>Home nVentory</h1>
        <h4>Menu</h4>
        <ul>
            <li> <a href ="inventory">Inventory</a></li>
            <li> <a href ="admin">Admin</a></li>
           <li> <a href ="category">Manage Category</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        <div>
            <h3>Manage Users</h3>
            <table class="usertable">
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Delete</th>
                    <th>Edit</th>
                    <th>Active</th>
                </tr>
                <c:forEach var="users" items="${users}">
                    <tr>
                        <td>${users.email}</td>
                        <td>${users.firstName}</td>
                        <td>${users.lastName}</td>
                        <td>
                            <form method ="POST" action="admin">
                                <input type="submit" value="delete">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="email" value="${users.email}">
                            </form>             
                        </td>
                        <td>
                            <form method ="GET" action="admin">
                                <input type="submit" value="edit">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="edit_email" value="${users.email}">
                            </form>             
                        </td>
                        <td> ${users.active}
                            <form method="POST" action="admin" >
                            <input type="submit" value="toggling" >
                            <input type="hidden" name ="action" value="toggling">
                            <input type="hidden" name="email" value="${users.email}" >
                           </form>
                        </td>
           </tr>
                </c:forEach>

            </table>
        </div>

        <c:choose>

            <c:when test="${euser != null}">
                <div>
                    <form method ="POST" action="admin">
                        <h2>Edit user</h2>
                        <label>Email:</label>
                        <input type="text" name ="email" value ="${euser.email}" >
                        <br>
                        <label>First Name:</label>
                        <input type="text" name ="firstName" value ="${euser.firstName}" >
                        <br>
                        <label>Last Name:</label>
                        <input type="text" name="lastName" value="${euser.lastName}" >
                        <br> 
                        <label>Password:</label>
                        <input type="text" name ="password" value="${euser.password}">
                        <br>
<!--                        <label>Role:</label>
                        <select name="role" value="${euser.password}">
                            <c:forEach var="role" items="${roles}">
                                <option value ="${role.roleId}">${role.roleName}</option>
                            </c:forEach>
                        </select>-->

                        <br>
                        <input type="submit" value="edit">
                        <input type="hidden" name ="action" value ="edit" >
                    </form>

                </div>
            </c:when>

            <c:otherwise>
                <div>
                    <form method ="post" action="admin">
                        <h2>Add user</h2>
                        <label>Email:</label>
                        <input type="text" name ="email" value ="" >
                        <br>
                        <label>First Name:</label>
                        <input type="text" name ="firstName" value ="" >
                        <br>
                        <label>Last Name:</label>
                        <input type="text" name="lastName" value="" >
                        <br> 
                        <label>Password:</label>
                        <input type="text" name ="password" value="">
                        <br>
                        <label>Role:</label>
                        <select name="role">
                            <c:forEach var="role" items="${roles}">
                                <option value ="${role.roleId}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <input type="submit" value="add">
                        <input type="hidden" name ="action" value ="add" >
                    </form>
                </div>
            </c:otherwise>
        </c:choose>


        </body>
    </html>
