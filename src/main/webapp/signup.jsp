<%-- 
    Document   : signup
    Created on : Dec 26, 2023, 3:41:59 PM
    Author     : lesan
--%>

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
            <input type="text" name="password" required>
            <br>
            <label for="surname">Surname</label>
            <input type="text" name="surname" required>
            <br>
            <label for="middleName">Middle name</label>
            <input type="text" name="middleName" required>
            <br>
            <label for="firstName">First name</label>
            <input type="text" name="firstName" required>
            <br>
            <label for="email">Email</label>
            <input type="text" name="email" required>
            <br>
            <label for="Phone">Phone</label>
            <input type="text" name="phone" required>
            <br>
            <button type="submit">Send</button>
        </form>
    </body>
</html>
