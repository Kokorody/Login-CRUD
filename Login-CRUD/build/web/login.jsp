<%-- 
    Document   : login
    Created on : Jul 19, 2024, 11:03:25 PM
    Author     : Kmsmr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="style.css">
    <title>Login</title>
</head>
<body>
    <div class="section-1 box"> 
    </div>
    <div class="container"> 
        <div class="signup-section">
            <form id="login-form" action="LoginServlet" method="post">
                <input type="email" id="login-email" placeholder="Email address" name="eMail" autocomplete="off">
                <span class="error" id="login-email-error"></span>
                <input type="password" id="login-password" placeholder="Password" name="pWord" autocomplete="off">
                <span class="error" id="login-password-error"></span>
                <button type="submit" class="btn">Login</button>
            </form>
        </div>
    </div>

    <script src="script.js"></script>
</body>
</html>

