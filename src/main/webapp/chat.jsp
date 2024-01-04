<%@page import="com.mycompany.simple_chatbot.model.Conversation"%>
<%@page import="com.mycompany.simple_chatbot.config.util.StringConstants"%>
<%@page import="com.mycompany.simple_chatbot.model.UserInfo"%>
<%@page import="com.mycompany.simple_chatbot.model.ChatMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chat</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        html, body {
            height: 100%;
            margin: 0;
        }

        .container {
            display: flex;
            height: 100vh;
        }

        #content {
            flex: 1;
            padding: 10px;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #chatbox {
            flex-grow: 1;
            overflow-y: auto;
            border: 1px solid #dee2e6;
            padding: 10px;
            position: relative;
        }

        .waiting-bubble {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 10px;
            background-color: #d6d8db;
            border-radius: 8px;
            display: none;
        }

        #userInputContainer {
            margin-top: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        #userInput {
            flex-grow: 1;
            margin-right: 10px;
        }
        
/*        #sidebar {
            flex: 0 0 20%;
            background-color: #f8f9fa;
            padding: 10px;
            border-right: 1px solid #dee2e6;
            height: 100vh;
        }*/
        
        #sidebar {
            flex: 0 0 20%;
            background-color: #f8f9fa;
            padding: 10px;
            border-right: 1px solid #dee2e6;
            height: 100vh;
            display: flex;
            flex-direction: column;
        }

        #sidebar .flex-col:first-child {
            flex: 0 0 100%;
            transition: flex 0.5s; /* Optional: Add a transition for a smooth change in flex value */
        }
        
        .flex-60p {
            flex: 0 0 60%!important;
        }
        
        .d-flex {
            display: flex;
        }
        
        .flex-d-col {
            flex-direction: column;
        }
    </style>
</head>
<body>
    
    <div class="w-100 container-fluid">
        <div class="row">
            <div id="sidebar">
                <div class="flex d-flex h-full w-full flex-col px-3 pb-3.5" style="flex-direction: column">
                    <div class="flex flex-col pt-2 empty:hidden dark:border-white/20" style="flex: 0 0 20%">
                        <div>
                            Hello, <span id="username"><%= request.getAttribute(StringConstants.CHAT_USER_ATTRIBUTE) %></span> !
                        </div>
                        <button>New Chat</button>
                    </div>
                    <div class="flex d-flex flex-col flex-1 flex-60p transition-opacity duration-500 -mr-2 pr-2 overflow-y-auto">
                        <ul id="listConversations">
                            <%
                                List<Conversation> conversations=(List)request.getAttribute(StringConstants.CONVERSATIONS_ATTRIBUTE);
                                for(int i=0; conversations!=null && i<conversations.size() ;i++){
                                    Conversation conservation=conversations.get(i);
                            %>
                            <li onclick="getMessagesByConversationId('<%=conservation.getId()%>')"><%=conservation.getName()%></li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <div class="flex flex-col pt-2 empty:hidden dark:border-white/20" style="flex: 0 0 20%">
                        <!-- Logout button -->
                        <button class="btn btn-primary mb-2">
                            <a href="/simple_chatbot/update-password" class="text-decoration-none text-light">
                                Update password
                            </a>
                    </button>
                        <!-- Logout button -->
                        <form action="logout" method="get" class="mb-3">
                            <button type="submit" class="btn btn-danger">Logout</button>
                        </form>
                    </div>
                </div>
            </div>

            <div id="content">
        <h1>Chatbot</h1>
        <div id="chatbox" class="border mb-3">
            <div class="waiting-bubble">Waiting for response...</div>
        </div>
        <div id="userInputContainer" class="border-top">
            <input type="text" id="userInput" class="form-control" placeholder="Type your message...">
            <button class="btn btn-primary" onclick="sendMessage()">Send</button>
        </div>
        
    </div>
            
        </div>
    </div>
    
    <!-- Bootstrap JS (Optional: Only if you are using Bootstrap JavaScript features) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <script> 
        var conversationToken = "";
        function generateUniqueToken() {
            // Generate a timestamp component
            const timestamp = new Date().getTime().toString(16);

            // Generate a random component
            const randomPart = Math.random().toString(16).substring(2);

            // Combine timestamp and random part
            const uniqueToken = timestamp + randomPart;

            return uniqueToken;
        }

        function getMessagesByConversationId(id) {
            var chatbox = $("#chatbox");
            conversationToken = id;
            $.ajax({
                url: "messages",
                type: "POST",
                data: {conversationId: id },
                success: function(response) {
                    chatbox.empty();
                    chatbox.append("<div class='waiting-bubble'>Waiting for response...</div>");
                    
                    response.forEach(function(item) {
                        chatbox.append("<p>User: " + item.message + "</p>");
                        chatbox.append("<p>Chatbot: " + item.response + "</p>");
                    });
               },
               error: function() {
                    console.log("Error in AJAX request");
                }
            });
        }

        function sendMessage() {
            var userInput = $("#userInput").val();
            var username = $("#username").text();
            console.log(username);
            var chatbox = $("#chatbox");
            var waitingBubble = chatbox.find(".waiting-bubble");
            
            if (conversationToken === "") {
                conversationToken = generateUniqueToken();
            }
            
            chatbox.append("<p>User: " + userInput + "</p>");

            // Display waiting bubble
            waitingBubble.show();

            // Disable input and send button temporarily
            $("#userInput, button").prop("disabled", true);


            // Add server-side logic here to process the user's input
            // You can use AJAX to send the user's input to the server (ChatbotServlet) and get the response.

            // Example AJAX code (requires jQuery):
            // AJAX POST request using jQuery
            $.ajax({
                url: "chat",
                type: "POST",
                data: {username: username, message: userInput, conversationToken: conversationToken },
                success: function(response) {
                    // Hide waiting bubble
                    waitingBubble.hide();

                    // Enable input and send button
                    $("#userInput, button").prop("disabled", false);

                    // Clear the input field after sending the message
                    $("#userInput").val("");

                    // Scroll to the bottom of the chatbox
                    chatbox.scrollTop(chatbox[0].scrollHeight);
                    
                    chatbox.append("<p>Chatbot: " + response + "</p>");
               },
               error: function() {
                    console.log("Error in AJAX request");
                }
            });
            
            // Clear the input field after sending the message
            $("#userInput").val("");

            // For simplicity, this example doesn't include the server-side logic.
            // You need to implement the logic in the ChatbotServlet class.
            }
            
        conversationToken = generateUniqueToken();
        
    </script>
    
</body>
</html>
