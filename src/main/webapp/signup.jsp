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
        <title>Sign Up</title>
        <!-- Link to Bootstrap CSS -->
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

            /* Define max-width for the form */
            .max-width-500 {
                max-width: 500px;
            }
        </style>
    </head>
    <body>
        <!-- Include Header -->
        <%@ include file="parts/header.jsp" %>

        <!-- Body Content -->
        <div class="container-fluid d-flex flex-column justify-content-center align-items-center py-3" style="min-height: 80vh;">
            <div class="text-center mb-4">
                <h2>Sign Up</h2>
            </div>
            <div class="w-100 max-width-500"> <!-- Set a maximum width for the form -->
                <form action="/simple_chatbot/signup" method="post" class="g-3 needs-validation" novalidate>
            <div class="mb-3">
                <label for="<%= StringConstants.USERNAME_PARAM %>" class="form-label">Username:</label>
                <input type="text" class="form-control" name="<%= StringConstants.USERNAME_PARAM %>" required>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.PASSWORD_PARAM %>" class="form-label">Password:</label>
                <input type="password" class="form-control" name="<%= StringConstants.PASSWORD_PARAM %>" required>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.SURNAME_PARAM %>" class="form-label">Surname:</label>
                <input type="text" class="form-control" name="<%= StringConstants.SURNAME_PARAM %>" required>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.MIDDLE_NAME_PARAM %>" class="form-label">Middle name:</label>
                <input type="text" class="form-control" name="<%= StringConstants.MIDDLE_NAME_PARAM %>" required>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.FIRST_NAME_PARAM %>" class="form-label">First name:</label>
                <input type="text" class="form-control" name="<%= StringConstants.FIRST_NAME_PARAM %>" required>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.EMAIL_PARAM %>" class="form-label">Email:</label>
                <input type="email" class="form-control" name="<%= StringConstants.EMAIL_PARAM %>" required>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.PHONE_PARAM %>" class="form-label">Phone:</label>
                <input type="tel" class="form-control" name="<%= StringConstants.PHONE_PARAM %>" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </div>
        </form>
            </div>
        </div>

        <!-- Include Footer -->
        <%@ include file="parts/footer.jsp" %>
    </body>
</html>
