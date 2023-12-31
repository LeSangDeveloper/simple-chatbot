<%-- 
    Document   : signup
    Created on : Dec 26, 2023, 3:41:59 PM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.config.util.StringConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="/simple_chatbot/signup" method="post">
            <label for="<%= StringConstants.USERNAME_PARAM %>">Username:</label>
            <input type="text" name="<%= StringConstants.USERNAME_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.PASSWORD_PARAM %>">Password</label>
            <input type="password" name="<%= StringConstants.PASSWORD_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.SURNAME_PARAM %>">Surname</label>
            <input type="text" name="<%= StringConstants.SURNAME_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.MIDDLE_NAME_PARAM %>">Middle name</label>
            <input type="text" name="<%= StringConstants.MIDDLE_NAME_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.FIRST_NAME_PARAM %>">First name</label>
            <input type="text" name="<%= StringConstants.FIRST_NAME_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.EMAIL_PARAM %>">Email</label>
            <input type="text" name="<%= StringConstants.EMAIL_PARAM %>" required>
            <br>
            <label for="<%= StringConstants.PHONE_PARAM %>">Phone</label>
            <input type="text" name="<%= StringConstants.PHONE_PARAM %>" required>
            <br>
            <button type="submit">Send</button>
        </form>
    </body>
</html>
