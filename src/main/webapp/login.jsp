<%-- 
    Document   : login
    Created on : Dec 18, 2023, 2:40:47 PM
    Author     : lesan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="/simple_chatbot/login" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" required>
            <br>
            <label for="password">Password</label>
            <input type="password" name="password" required>
            <br>
            <button type="submit">Send</button>
        </form>
        <div>
            Don't have account ? <a href="/simple_chatbot/signup">Sign Up</a>
        </div>
    </body>
</html>
