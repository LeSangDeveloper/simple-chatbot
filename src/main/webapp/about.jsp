<%-- 
    Document   : about
    Created on : Jan 8, 2024, 5:18:13 PM
    Author     : lesan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About</title>
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
            
            
            .btn-pink {
                background: #c1177c;
            }   
        
            .btn-pink:hover {
                background: #a50064;
            }
            
            .link-pink {
                color: #c1177c;
            }   
        
            .link-pink:hover {
                color: #a50064;
            }
        </style>
    </head>
    <body>

        <!-- Include Header -->
        <%@ include file="parts/header.jsp" %>
    
<!-- Body -->
<div class="container my-5 text-center">
    <h1 class="display-4">Welcome to Our Simple Chatbot</h1>
    <p class="lead">
        My Simple Chatbot is a project developed as part of the Network Programming course at Yuan Ze university. It aims to provide an interactive and engaging conversational experience for users.
    </p>
    
    <h2 class="mt-5">About the Project</h2>
    <p>
        The Simple Chatbot project is designed to demonstrate the concepts of network programming, web development, and natural language processing. It allows users to interact with an AI-powered chatbot, which can answer questions, provide information, and engage in casual conversations.
    </p>
    
    <h2 class="mt-5">Key Features</h2>
    <ul class="list-unstyled">
        <li><i class="bi bi-chat-left-quote h4"></i> Conversational Interface: Our chatbot offers a user-friendly conversational interface, making it easy for users to interact.</li>
        <li><i class="bi bi-chat-left-quote h4"></i> Natural Language Processing: The chatbot leverages natural language processing techniques to understand and respond to user queries.</li>
        <li><i class="bi bi-chat-left-quote h4"></i> Customizable Responses: You can customize the chatbot's responses to suit your specific use cases.</li>
        <li><i class="bi bi-chat-left-quote h4"></i> Web-Based Interface: Access the chatbot through a web browser on your computer or mobile device.</li>
    </ul>
    
    <h2 class="mt-5">How to Use</h2>
    <p>
        Using our Simple Chatbot is easy. Simply type your questions or messages in the input field, press "Send," and the chatbot will provide responses based on its trained knowledge.
    </p>
    
    <h2 class="mt-5">Contact Us</h2>
    <p>
        If you have any questions, feedback, or suggestions regarding our Simple Chatbot, please feel free to contact us at <a href="/simple_chatbot/contact.jsp">contact</a> page.
    </p>
</div>
    
    
        <!-- Include Footer -->
        <%@ include file="parts/footer.jsp" %>
    </body>
</html>
