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
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
        <link href="http://localhost:3000/css/bootstrap.min.css" rel="stylesheet">
        <style>
        body {
            margin: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Ensure the body takes up at least the full height of the viewport */
        }

        .container-fluid {
            flex: 1;
        }
    </style>
    </head>
    
    <body>

    <!-- Include Header -->
    <%@ include file="parts/header.jsp" %>

    <!-- Body -->
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center" style="min-height: 80vh;">
        <div class="text-center mb-3">
            <h2>Login</h2>
        </div>
        <form action="/simple_chatbot/login" method="post" class="needs-validation" novalidate style="max-width: 400px; width: 100%;">
            <div class="mb-3">
                <label for="<%= StringConstants.USERNAME_PARAM %>" class="form-label">Username:</label>
                <input type="text" class="form-control" name="<%= StringConstants.USERNAME_PARAM %>" required>
                <div class="invalid-feedback">Please enter your username.</div>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.PASSWORD_PARAM %>" class="form-label">Password:</label>
                <input type="password" class="form-control" name="<%= StringConstants.PASSWORD_PARAM %>" required>
                <div class="invalid-feedback">Please enter your password.</div>
            </div>
            <button class="btn btn-primary" type="submit">Login</button>
        </form>
        <div class="mt-3">
            Don't have an account? <a href="/simple_chatbot/signup">Sign Up</a>
        </div>
    </div>

    <!-- Include Footer -->
    <%@ include file="parts/footer.jsp" %>
    
</body>
</html>
