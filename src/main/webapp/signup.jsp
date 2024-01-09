<%-- 
    Document   : signup
    Created on : Dec 26, 2023, 3:41:59 PM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.config.util.ErrorMessageUtils"%>
<%@page import="com.mycompany.simple_chatbot.config.util.StringConstants"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <!-- Link to Bootstrap CSS -->
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
            <% if (request.getAttribute(ErrorMessageUtils.PARAM_SIGNUP_ERROR) != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute(ErrorMessageUtils.PARAM_SIGNUP_ERROR) %>
            </div>
            <% } %>
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
                <% if (request.getAttribute(StringConstants.SIGNUP_SURNAME) != null) { %>
                    <input type="text" class="form-control" value="<%=request.getAttribute(StringConstants.SIGNUP_SURNAME)%>" name="<%= StringConstants.SURNAME_PARAM %>" required>
                <% } else { %>
                    <input type="text" class="form-control" name="<%= StringConstants.SURNAME_PARAM %>" required>
                <% } %>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.MIDDLE_NAME_PARAM %>" class="form-label">Middle name:</label>
                <% if (request.getAttribute(StringConstants.SIGNUP_MIDDLE_NAME) != null) { %>
                    <input type="text" class="form-control" value="<%=request.getAttribute(StringConstants.SIGNUP_MIDDLE_NAME)%>" name="<%= StringConstants.MIDDLE_NAME_PARAM %>" required>
                <% } else { %>
                    <input type="text" class="form-control" name="<%= StringConstants.MIDDLE_NAME_PARAM %>" required>
                <% } %>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.FIRST_NAME_PARAM %>" class="form-label">First name:</label>
                <% if (request.getAttribute(StringConstants.SIGNUP_FIRST_NAME) != null) { %>
                    <input type="text" class="form-control" value="<%=request.getAttribute(StringConstants.SIGNUP_FIRST_NAME)%>" name="<%= StringConstants.FIRST_NAME_PARAM %>" required>
                <% } else { %>
                    <input type="text" class="form-control" name="<%= StringConstants.FIRST_NAME_PARAM %>" required>
                <% } %>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.EMAIL_PARAM %>" class="form-label">Email:</label>
                <% if (request.getAttribute(StringConstants.SIGNUP_EMAIL) != null) { %>
                    <input type="email" class="form-control" value="<%=request.getAttribute(StringConstants.SIGNUP_EMAIL)%>" name="<%= StringConstants.EMAIL_PARAM %>" required>
                <% } else { %>
                    <input type="email" class="form-control" name="<%= StringConstants.EMAIL_PARAM %>" required>
                <% } %>
            </div>
            <div class="mb-3">
                <label for="<%= StringConstants.PHONE_PARAM %>" class="form-label">Phone:</label>
                <% if (request.getAttribute(StringConstants.SIGNUP_PHONE) != null) { %>
                    <input type="tel" class="form-control" value="<%=request.getAttribute(StringConstants.SIGNUP_PHONE)%>" name="<%= StringConstants.PHONE_PARAM %>" required>
                <% } else { %>
                    <input type="tel" class="form-control" name="<%= StringConstants.PHONE_PARAM %>" required>
                <% } %>
            </div>
            <div class="text-center">
                <button type="submit" class="btn text-light" style="background: #c1177c;">Sign Up</button>
            </div>
        </form>
            </div>
        </div>

        <!-- Include Footer -->
        <%@ include file="parts/footer.jsp" %>
        <script>
            // Wait for the DOM to load
            document.addEventListener('DOMContentLoaded', (event) => {
            const form = document.querySelector('form.needs-validation');

            form.addEventListener('submit', function(event) {
                let isValid = true;

                const username = this.querySelector('input[name="username"]');
                const password = this.querySelector('input[name="password"]');
                const surname = this.querySelector('input[name="surname"]');
                const middleName = this.querySelector('input[name="middleName"]');
                const firstName = this.querySelector('input[name="firstName"]');
                const phone = this.querySelector('input[name="phone"]');
                const email = this.querySelector('input[name="email"]');

                // Username validation
                if (username.value.trim().length < 5 || /[^a-zA-Z0-9]/.test(username.value)) {
                    alert('Username must be at least 5 characters long and contain no special characters or spaces.');
                    isValid = false;
                }

                // Password validation
                if (password.value.trim().length < 6 || /[^a-zA-Z0-9]/.test(password.value)) {
                    alert('Password must be at least 6 characters long and contain no special characters or spaces.');
                    isValid = false;
                }

                // Surname, Middle Name, and First Name validation
                if (!surname.value.trim() || !middleName.value.trim() || !firstName.value.trim()) {
                    alert('Surname, Middle Name, and First Name cannot be empty.');
                    isValid = false;
                }

                // Phone validation
                if (phone.value.trim().length < 7 || /[^0-9]/.test(phone.value)) {
                    alert('Phone must contain only numbers and be longer than 6 characters.');
                    isValid = false;
                }

                // Email validation using simple pattern, you can use more complex regex for stricter validation
                const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(email.value)) {
                    alert('Enter a valid email address.');
                    isValid = false;
                }

                // Prevent form submission if validation fails
                if (!isValid) {
                    event.preventDefault();
                }
            });

            // Function to validate each input field
            function validateInput(input, regex, message) {
                    if (regex.test(input.value)) {
                        input.setCustomValidity('');
                    } else {
                        input.setCustomValidity(message);
                    }
                }
            });
        </script>

    </body>
</html>
