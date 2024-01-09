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
        <title>Contact</title>
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
<div class="container my-5 text-center" style="min-height: 67vh;">
    <h1 class="display-4">Contact Us</h1>
    <p class="lead">Feel free to reach out to us with any inquiries or feedback.</p>
    
    <div class="row">
        <div class="col-md-6">
            <h2>Contact Details</h2>
            <ul class="list-unstyled">
                <li><strong>Name:</strong> Le Sang</li>
                <li><strong>Email:</strong> <a href="sang@mail.com">sang@mail.com</a></li>
                <li><strong>Phone:</strong> +1234567890</li>
                <li><strong>Address:</strong> No. 135, Yuandong Rd., Zhongli Dist., <br/>Taoyuan City 320315 , Taiwan (R.O.C.)</li>
            </ul>
        </div>
        
        <div class="col-md-6">
            <h2>Contact Form</h2>
            <form>
                <div class="mb-3">
                    <input type="text" class="form-control" placeholder="Your Name" required>
                </div>
                <div class="mb-3">
                    <input type="email" class="form-control" placeholder="Your Email" required>
                </div>
                <div class="mb-3">
                    <textarea class="form-control" rows="5" placeholder="Your Message" required></textarea>
                </div>
                <button type="submit" class="btn btn-pink text-light">Send Message</button>
            </form>
        </div>
    </div>
</div>
    
    
        <!-- Include Footer -->
        <%@ include file="parts/footer.jsp" %>
    </body>
</html>
