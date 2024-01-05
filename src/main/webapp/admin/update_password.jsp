<%-- 
    Document   : update_password
    Created on : Jan 4, 2024, 2:12:53 PM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.config.util.StringConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Password</title>
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
        </style>
    </head>
    <body>
        <!-- Include Header -->
        <nav class="navbar navbar-expand-lg navbar-light py-4" style="background: #c1177c">
            <div class="container">
                <a class="navbar-brand text-white" href="/simple_chatbot/">Hello, Admin</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="btn btn-danger" href="/simple_chatbot/logout" role="button">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Body -->
        <div class="container-fluid d-flex flex-column justify-content-center align-items-center" style="min-height: 80vh;">
            <div class="text-center mb-3">
                <h2>Update Password</h2>
            </div>
            <form action="/simple_chatbot/admin/update-password" method="post" class="needs-validation" novalidate style="max-width: 400px; width: 100%;">
                <div class="mb-3">
                    <label for="<%= StringConstants.USERNAME_PARAM %>" class="form-label">Account</label>
                    <input type="text" class="form-control" value="<%=request.getAttribute(StringConstants.USERNAME_PARAM)%>" name="<%= StringConstants.USERNAME_PARAM %>" disabled="disabled" required>
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" value="<%=request.getAttribute(StringConstants.USERNAME_PARAM)%>" name="<%= StringConstants.USERNAME_PARAM %>" hidden required>
                </div>
                <div class="mb-3">
                    <label for="<%= StringConstants.NEW_PASSWORD_PARAM %>" class="form-label">Old Password:</label>
                    <input type="password" class="form-control" name="<%= StringConstants.NEW_PASSWORD_PARAM %>" required>
                    <div class="invalid-feedback">Please enter your new password.</div>
                </div>
                <div class="d-flex justify-content-center">
                    <button class="btn btn-pink text-light" type="submit">Confirm</button>
                </div>  
            </form>
        </div>

        <!-- Include Footer -->
        <%@ include file="../parts/footer.jsp" %>
    </body>
</html>
