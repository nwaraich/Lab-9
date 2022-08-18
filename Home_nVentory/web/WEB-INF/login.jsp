

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Inventory</title>
    </head>
    <body>
        <h1>Home nVentory</h1>
        <h2>Login</h2>
        <form method="POST" action="login">
            <label>Email:</label>
            <input type="text" name="e_email" value="">
            <br>
            <label>Password:</label>
            <input type="text" name ="p_password" value ="${password}">
            <br>
            <input type="submit" value="Submit">
            <br>
            <p>${message}</p>

        </form>

            <li> <a href ="register">Registration</a></li>
        
    </body>
</html>
