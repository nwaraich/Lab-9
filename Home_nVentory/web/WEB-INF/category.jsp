
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Category</title>
    </head>
    <body>
        <h1>Manage Category</h1>

        <ul>
            <li><a href ="inventory">Inventory</a></li>
            <li> <a href ="admin">Admin</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        <div>
            <h3>Manage Categories </h3>
            <table
                class ="categories">
                <tr>
                    <th>Category</th>
                    <th>Edit</th>
                </tr>

                <c:forEach var ="cate" items = "${categorylist}">
                    <tr>
                        <td>${cate.categoryName}</td>
                        <td>
                            <form method="GET" action = "category" >
                                <input type="submit" value="edit">
                                <input type="hidden" name="object" value="edit">
                                <input type="hidden" name="cateid" value="${cate.categoryId}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>


        <c:choose>
            <c:when test=" ${cateID != null}">
                <h4>Edit Category</h4>
                <form method ="POST" action ="category">
                    <br>
                    <label>Category Name:</label>
                    <input type="text" name="category_name" value ="${cateID.categoryName}">
                    <br>
                    <input type="submit" value="update">
                    <input type="hidden" name="action_cate" value="update">
                </form>      
            </c:when>

            <c:otherwise>
                <div>
                    <h3>Add Category</h3>
                    <form method="POST" action="category"> 
                        <br>
                        <label>Category Name:</label>
                        <input type="text" name="category_name" value="" >
                        <br>
                        <input type="submit" value="add">
                        <input type="hidden" name="action_cate" value="add">
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
