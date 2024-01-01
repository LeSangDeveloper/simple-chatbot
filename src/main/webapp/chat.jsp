<%@page import="com.mycompany.simple_chatbot.model.UserInfo"%>
<%@page import="com.mycompany.simple_chatbot.model.ChatMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Simple Chat</title>
</head>
<body>
    <h1>Simple Chat</h1>

    <%
        UserInfo info = (UserInfo)request.getSession().getAttribute("userInfo");
    %>
    
        <!-- Logout button -->
    <form action="logout" method="get">
        <input type="submit" value="Logout">
    </form>
    
    <form action="chat" method="post">
        <input type="hidden" name="username" value="<%=info.getUsername()%>" required>
        <label for="message">Message:</label>
        <input type="text" name="message" required>
        <br>
        <button type="submit">Send</button>
    </form>

    <div>
        <h2>Chat History</h2>
        <ul>
            <%
                List<ChatMessage> chatMessages = (List<ChatMessage>) request.getAttribute("chatMessages");
                if (chatMessages != null) {
                    for (ChatMessage chatMessage : chatMessages) {
            %>
                        <li><strong><%= chatMessage.getUsername() %>:</strong> <%= chatMessage.getMessage() %></li>
                        <li><strong>chatbot:</strong> <%= chatMessage.getResponse() %></li>
                        <div style="margin-top: 20px;"></div>
            <%
                    }
                }
            %>
        </ul>
    </div>
</body>
</html>