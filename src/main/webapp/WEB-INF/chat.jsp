<%@page import="com.mycompany.simple_chatbot.ChatMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Simple Chat</title>
</head>
<body>
    <h1>Simple Chat</h1>

    <form action="chat" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" required>
        <br>
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
            <%
                    }
                }
            %>
        </ul>
    </div>
</body>
</html>