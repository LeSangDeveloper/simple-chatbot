<%-- 
    Document   : admin
    Created on : Jan 4, 2024, 1:00:47 PM
    Author     : lesan
--%>

<%@page import="com.mycompany.simple_chatbot.model.Account"%>
<%@page import="java.util.List"%>
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
            min-height: 100vh;
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
    <!-- Header -->
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
    <div class="container-fluid d-flex flex-column justify-content-center align-items-center min-vh-80">
        <div class="text-center my-4">
            <h2 style="color: #c1177c">ADMIN PAGE</h2>
        </div>
        <table class="table table-bordered table-hover my-3">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Surname</th>
                    <th>Middle Name</th>
                    <th>First Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterate through the list of accounts -->
                <%
                List<Account> accounts = (List) request.getAttribute(StringConstants.ATTRIBUTE_ACCOUNTS_LIST);
                for (int i = 0; accounts != null && i < accounts.size(); i++) {
                    Account account = accounts.get(i);
                %>
                <tr>
                    <td><%=account.getId()%></td>
                    <td><%=account.getSurname()%></td>
                    <td><%=account.getMiddleName()%></td>
                    <td><%=account.getFirstName()%></td>
                    <td><%=account.getEmail()%></td>
                    <td><%=account.getPhone()%></td>
                    <td>
                        <a href="/simple_chatbot/admin/update-password?username=<%=account.getId()%>" class="btn btn-pink btn-sm text-light" >Update Password</a>
                    </td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>

    <!-- Include Footer -->
    <%@ include file="../parts/footer.jsp" %>

    <!-- Bootstrap JS (Optional: Only if you are using Bootstrap JavaScript features) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <!-- Add any custom scripts here -->
    <script> 
        function updatePassword(id) {
                
        }
    </script> 
</body>
</html>
