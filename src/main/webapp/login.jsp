<%-- 
    Document   : login
    Created on : Dec 18, 2023, 2:40:47 PM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.config.util.StringConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="/simple_chatbot/login" method="post">
            <label for="<%= StringConstants.USERNAME_PARAM %>">Username:</label>
            <input type="text" name="<%= StringConstants.USERNAME_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.PASSWORD_PARAM %>">Password</label>
            <input type="password" name="<%= StringConstants.PASSWORD_PARAM %>" required>
            <br>
            <button type="submit">Send</button>
        </form>
        <div>
            Don't have account ? <a href="/simple_chatbot/signup">Sign Up</a>
        </div>
    </body>
</html>
