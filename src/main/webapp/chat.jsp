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

        #sidebar {
            flex: 0 0 20%;
            background-color: #f8f9fa;
            padding: 10px;
            border-right: 1px solid #dee2e6;
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
    </style>
</head>
<body>
    
    <div class="w-100 container-fluid">
        <div class="row">
                %>
            <div id="sidebar" class="col-2">
                <div class="flex h-full w-full flex-col">
                    <div class="flex-col flex-1 transition-opacity duration-500 -mr-2 pr-2 overflow-y-auto">
                        <ul>
                            <li>Chat 1</li>
                        </ul>
                    </div>
                    <div class="flex flex-col pt-2 empty:hidden dark:border-white/20">
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
        function sendMessage() {
            var userInput = $("#userInput").val();
            var chatbox = $("#chatbox");
            var waitingBubble = chatbox.find(".waiting-bubble");

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
                data: {username: 'user', message: userInput },
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
    </script>
    
</body>
</html>
