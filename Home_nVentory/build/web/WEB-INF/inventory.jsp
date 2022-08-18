
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Inventory</title>
    </head> 
    <body>

        <h1>Home Inventory for ${email}</h1>
        <h4>Menu</h4>
        <ul>
            <li> <a href ="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href ="account">Manage Account</a></li>
            <li> <a href="login?logout">Logout</a></li>
        </ul>

        <h2>Inventory for ${email}</h2>
        <table class="usertable">
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th> 
                <th>Delete</th>
                <th>Edit</th>
            </tr>

            <c:forEach var="item" items="${itemlist}">
                <tr>
                    <td>${item.category.categoryName}</td>
                    <td>${item.itemName}</td>
                    <td>$${item.price}</td>
                    <td>
                        <form method="POST" action="inventory">
                            <input type="submit" value="delete">
                            <input type="hidden" name ="action" value ="delete">
                            <input type="hidden" name="ItemID" value="${item.itemId}">

                        </form>
                    </td>
                    <td>
                        <form method="GET" action="inventory">
                            <input type="submit" value="edit">
                            <input type="hidden" name ="action" value ="edit">
                            <input type="hidden" name="ItemEdit" value="${item.itemId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>


        <c:choose>
            <c:when test="${item_id != null}">
                <h3>Edit Item</h3>
                <form method="POST" action="inventory"> 
                    <label>Category</label>
                    <select name ="category" >
                        <c:forEach var="cate" items ="${categories}">
                            <option value ="${cate.categoryId}" >${cate.categoryName}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Item Name:</label>
                    <input type="text" name="item_name" value="${item_id.itemName}" >
                    <br>
                    <label>Price:</label>
                    <input type="text" name="price" value="${item_id.price}"  >
                    <br>
                    <input type="submit" value="update">
                    <input type="hidden" name="action" value="update">
                </form>
            </c:when>

            <c:otherwise>
                <h3>Add Item</h3>
                <form method="POST" action="inventory"> 
                    <label>Category</label>
                    <select name ="category">
                        <c:forEach var="cate" items ="${categories}">
                            <option value ="${cate.categoryId}" >${cate.categoryName}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Item Name:</label>
                    <input type="text" name="item_name" value="" >
                    <br>
                    <label>Price:</label>
                    <input type="text" name="price" value=""  >
                    <br>

                    <input type="submit" value="add">
                    <input type="hidden" name="action" value="add">

                </form>
            </c:otherwise>
        </c:choose>

    </body>
</html>
