<%@page import="com.mycompany.simple_chatbot.model.UserInfo"%>
<%@page import="com.mycompany.simple_chatbot.model.ChatMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Simple Chat</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 mx-auto">
                <h1 class="text-center">Simple Chat</h1>

                <%
                    UserInfo info = (UserInfo)request.getSession().getAttribute("userInfo");
                %>

                <!-- Logout button -->
                <form action="logout" method="get" class="mb-3 text-end">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </form>

                <form action="chat" method="post" class="mb-3">
                    <input type="hidden" name="username" value="<%=info.getUsername()%>" required>
                    <div class="mb-3">
                        <label for="message" class="form-label">Message:</label>
                        <input type="text" name="message" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Send</button>
                </form>

                <div>
                    <h2 class="mb-3">Chat History</h2>
                    <ul class="list-group">
                        <%
                            List<ChatMessage> chatMessages = (List<ChatMessage>) request.getAttribute("chatMessages");
                            if (chatMessages != null) {
                                for (ChatMessage chatMessage : chatMessages) {
                        %>
                                    <li class="list-group-item">
                                        <strong><%= chatMessage.getUsername() %>:</strong> <%= chatMessage.getMessage() %>
                                    </li>
                                    <li class="list-group-item">
                                        <strong>chatbot:</strong> <%= chatMessage.getResponse() %>
                                    </li>
                                    <div style="margin-top: 20px;"></div>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS (Optional: Only if you are using Bootstrap JavaScript features) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
