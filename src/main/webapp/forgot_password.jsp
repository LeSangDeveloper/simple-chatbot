<%-- 
    Document   : forgot_password
    Created on : Jan 1, 2024, 8:22:25 PM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.config.util.StringConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Forgot Password</h2>
        <form action="forgotPassword" method="post">
            Username (or Email): <input type="text" name="<%= StringConstants.USERNAME_PARAM %>" required>
        <br>
        <input type="submit" value="Reset Password">
    </form>
</body>
</html>
