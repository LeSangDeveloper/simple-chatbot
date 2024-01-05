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
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            padding: 10px;
        }

        .waiting-bubble {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 10px;
            background-color: #f8f9fa;
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
        
        .selected {
            background: black;
            color: white;
        }
        
        /* Hide scrollbar by default */
        #listConversations {
            overflow-y: hidden;
        }

        /* Show scrollbar on hover */
        #listConversations:hover {
            overflow-y: auto;
        }
    </style>
</head>
<body>
    
    <div class="container-fluid h-100">
        <div class="row h-100">
            <!-- Sidebar -->
            <div id="sidebar" class="col-md-4 col-lg-3 d-flex flex-column border-end">
                <!-- User greeting -->
                <div class="p-3 border-bottom">
                    <h5>Hello, <span id="username"><%= request.getAttribute(StringConstants.ATTRIBUTE_CHAT_USER) %></span>!</h5>
                    <button class="btn btn-success mt-2" onclick="newChat()">New Chat</button>
                </div>
                <!-- Conversations list -->
                <ul id="listConversations" class="list-group list-group-flush">
                    
                            <%
                            List<Conversation> conversations = (List) request.getAttribute(StringConstants.ATTRIBUTE_CONVERSATIONS);
                            for (int i = 0; conversations != null && i < conversations.size(); i++) {
                                Conversation conservation = conversations.get(i);
                            %>
                                <li onclick="getMessagesByConversationId('<%=conservation.getId()%>')"
                                data-conversation-id="<%=conservation.getId()%>">
                                <%=conservation.getName()%>
                                </li>
                            <%
                                }
                            %>
                    
                </ul>
                <!-- Bottom section -->
                <div class="mt-auto p-3">
                    <a href="/simple_chatbot/update-password" class="btn btn-primary w-100 text-decoration-none text-light mb-2">
                        Update password
                    </a>
                    <form action="logout" method="get">
                        <button type="submit" class="btn btn-danger w-100">Logout</button>
                    </form>
                </div>
            </div>
            
            <!-- Chat content -->
            <div id="content" class="col-md-8 col-lg-9 d-flex flex-column">
                <div id="chatbox" class="flex-grow-1 bg-white overflow-auto p-3">
                    <div class="waiting-bubble">Waiting for response...</div>
                    <!-- Messages will be loaded here -->
                </div>
                <div id="userInputContainer" class="input-group p-3">
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
        
        $(document).ready(function() {
            // Get screen height
            var screenHeight = $(window).height();
            
            // Set max-height for listConversations
            var maxListHeight = screenHeight * 0.5; // 80% of screen height
            $('#listConversations').css('max-height', maxListHeight + 'px');
            
            var listConversations = $('#listConversations');

            // Hide scrollbar by default
            listConversations.css('overflow-y', 'hidden');

            // Show scrollbar on hover
            listConversations.hover(
                function() {
                    $(this).css('overflow-y', 'auto');
                },
                function() {
                    $(this).css('overflow-y', 'hidden');
                }
            );
        });
        
        function newChat() {
            var chatbox = $("#chatbox");
            conversationToken = "";
            $("#listConversations li").removeClass("selected");
            chatbox.empty();
            chatbox.append("<div class='waiting-bubble'>Waiting for response...</div>");
        }
        
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
    
            // Remove the "selected" class from all list items
            $("#listConversations li").removeClass("selected");

            // Add the "selected" class to the clicked list item
            $("#listConversations li[data-conversation-id='" + id + "']").addClass("selected");

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
            var chatbox = $("#chatbox");
            var waitingBubble = chatbox.find(".waiting-bubble");
            var listConversations = $("#listConversations");
            var isNewConversation = false;
            if (conversationToken === "") {
                conversationToken = generateUniqueToken();
                isNewConversation = true;
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

                    if (isNewConversation) {
                        console.log("new conversation");
                        var onClickFront = 'onClick="';
                        var onClickFunc = "getMessagesByConversationId('"+ conversationToken +"')";  
                        var temp = onClickFront + onClickFunc + '"';
                        listConversations.prepend("<li " + temp + " " +
                                "data-conversation-id=" + conversationToken + ">" +
                                userInput + "</li>");
                        
                        $("#listConversations li[data-conversation-id='" + conversationToken + "']").on('click', 'li.conversation-item', function() {
                            var conversationId = $(this).data('conversation-id');
                            getMessagesByConversationId(conversationId);
                        });
                        
                        $("#listConversations li[data-conversation-id='" + conversationToken + "']").addClass("selected");
                    }

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
        
    </script>
    
</body>
</html>
