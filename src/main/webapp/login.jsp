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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
            
            
            .btn-pink {
                background: #c1177c;
            }   
        
            .btn-pink:hover {
                background: #a50064;
            }
            
            .link-pink {
                color: #c1177c;
            }   
        
            .link-pink:hover {
                color: #a50064;
            }
        </style>
        <script>
            function validateForm() {
                var username = document.forms["loginForm"]["<%= StringConstants.USERNAME_PARAM %>"].value;
                var password = document.forms["loginForm"]["<%= StringConstants.PASSWORD_PARAM %>"].value;

                // Check if username is empty
                if (username === "") {
                    alert("Please enter your username.");
                    return false;
                }

                // Check if username contains special characters or spaces
                if (!/^[a-zA-Z0-9]+$/.test(username)) {
                    alert("Username should not contain special characters or spaces.");
                    return false;
                }

                // Check if username has at least 5 characters
                if (username.length < 5) {
                    alert("Username should have at least 5 characters.");
                    return false;
                }

                // Check if password is empty
                if (password === "") {
                    alert("Please enter your password.");
                    return false;
                }

                // Check if password contains spaces
                if (/\s/.test(password)) {
                    alert("Password should not contain spaces.");
                    return false;
                }

                // Check if password has at least 6 characters
                if (password.length < 6) {
                    alert("Password should have at least 6 characters.");
                    return false;
                }

                // If all checks pass, allow form submission
                return true;
            }
        </script>
    </head>
    
    <body>

    <!-- Include Header -->
    <%@ include file="parts/header.jsp" %>

    <!-- Body -->
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center" style="min-height: 80vh;">        
        <div class="text-center mb-3">
            <h2>Login</h2>
        </div>
        <form action="/simple_chatbot/login" method="post" class="needs-validation" novalidate style="max-width: 400px; width: 100%;" onsubmit="return validateForm()" name="loginForm">            
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
            <% if (request.getAttribute("loginError") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("loginError") %>
            </div>
            <% } %>
            <div class="d-flex justify-content-center">
                <button class="btn btn-pink text-light" type="submit">Login</button>
            </div>  
        </form>
        <div class="mt-3">
            Don't have an account? <a class="link-pink" href="/simple_chatbot/signup">Sign Up</a>
        </div>
    </div>

    <!-- Include Footer -->
    <%@ include file="parts/footer.jsp" %>
    
</body>
</html>
