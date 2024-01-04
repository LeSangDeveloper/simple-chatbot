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
<!--        <link href="http://localhost:3000/css/bootstrap.min.css" rel="stylesheet">-->
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
        <!-- header.jsp -->
        <nav class="navbar navbar-expand-lg navbar-light py-16" style="padding-top: 1.375rem; padding-bottom: 1.375rem;background: #e5cb5e; color: #5860c3;">
            <div class="container-fluid">
                <a class="navbar-brand" href="/simple_chatbot/admin" style="color: #5860c3;">
                    <span>Hello, Chatbot Admin</span>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <button class="btn btn-danger">
                                <a href="/simple_chatbot/logout" class="text-decoration-none text-light">
                                    Logout
                                </a>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    
        <!-- Body -->
        <div class="container-fluid d-flex flex-column justify-content-center align-items-center" style="min-height: 80vh;">
            <div class="text-center mb-3">
                <h2 style="color: #511b11">ADMIN PAGE</h2>
            </div>
            <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Surname</th>
                <th>Middle Name</th>
                <th>First Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate through the list of accounts -->
            <%
                List<Account> accounts=(List)request.getAttribute(StringConstants.ATTRIBUTE_ACCOUNTS_LIST);
                for(int i=0; accounts!=null && i<accounts.size() ;i++){
                    Account account=accounts.get(i);
                    %>
                        <tr>
                            <td><%=account.getId()%></td>
                            <td><%=account.getSurname()%></td>
                            <td><%=account.getMiddleName()%></td>
                            <td><%=account.getFirstName()%></td>
                            <td><%=account.getEmail()%></td>
                            <td><%=account.getPhone()%></td>
                        </tr>
                    <%
                }
            %>
        </tbody>
    </table>
        </div>
        
        <!-- Include Footer -->
        <%@ include file="../parts/footer.jsp" %>
        
    </body>
</html>
