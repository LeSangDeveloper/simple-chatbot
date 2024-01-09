<%-- 
    Document   : update_password
    Created on : Jan 4, 2024, 11:24:49 AM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.config.util.ErrorMessageUtils"%>
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
        <script>
            function validateForm() {
                var old_password = document.forms["updatePasswordForm"]["<%= StringConstants.OLD_PASSWORD_PARAM %>"].value;
                var new_password = document.forms["updatePasswordForm"]["<%= StringConstants.NEW_PASSWORD_PARAM %>"].value;

                // Check if old password is empty
                if (old_password === "") {
                    alert("Please enter your old password.");
                    return false;
                }

                // Check if old password contains spaces
                if (/\s/.test(old_password)) {
                    alert("Old password should not contain spaces.");
                    return false;
                }

                // Check if old password has at least 6 characters
                if (old_password.length < 6) {
                    alert("Old password should have at least 6 characters.");
                    return false;
                }

                // Check if new password is empty
                if (new_password === "") {
                    alert("Please enter your new password.");
                    return false;
                }
                
                // Check if new password contains spaces
                if (/\s/.test(new_password)) {
                    alert("new password should not contain spaces.");
                    return false;
                }

                // Check if new password has at least 6 characters
                if (new_password.length < 6) {
                    alert("New password should have at least 6 characters.");
                    return false;
                }

                // If all checks pass, allow form submission
                return true;
            }
            
            function cancelUpdate() {
                // Navigate back to the previous page
                window.history.back();
            }
        </script>
    </head>
    <body>
        <!-- Include Header -->
        <%@ include file="parts/header.jsp" %>

        <!-- Body -->
        <div class="container-fluid d-flex flex-column justify-content-center align-items-center" style="min-height: 80vh;">
            <div class="text-center mb-3">
                <h2>Update Password</h2>
            </div>
            <form action="/simple_chatbot/update-password" method="post" class="needs-validation" novalidate style="max-width: 400px; width: 100%;" onsubmit="return validateForm()" name="updatePasswordForm">
                <div class="mb-3">
                    <label for="<%= StringConstants.OLD_PASSWORD_PARAM %>" class="form-label">Old Password</label>
                    <input type="password" class="form-control" name="<%= StringConstants.OLD_PASSWORD_PARAM %>" required>
                    <div class="invalid-feedback">Please enter your old password.</div>
                </div>
                <div class="mb-3">
                    <label for="<%= StringConstants.NEW_PASSWORD_PARAM %>" class="form-label">New Password:</label>
                    <input type="password" class="form-control" name="<%= StringConstants.NEW_PASSWORD_PARAM %>" required>
                    <div class="invalid-feedback">Please enter your new password.</div>
                </div>
                <% if (request.getAttribute(ErrorMessageUtils.PARAM_UPDATE_PASSWORD_ERROR) != null) { %>
                <div class="alert alert-danger">
                    <%= request.getAttribute(ErrorMessageUtils.PARAM_UPDATE_PASSWORD_ERROR) %>
                </div>
                <% } %>
                <div class="d-flex justify-content-center">
                    <button class="btn btn-pink text-light" style="margin-right: 1.125rem" type="submit">Confirm</button>
                    <button class="btn btn-secondary" type="button" onclick="cancelUpdate()">Cancel</button>
                </div>   
            </form>
        </div>

        <!-- Include Footer -->
        <%@ include file="parts/footer.jsp" %>
    </body>
</html>
