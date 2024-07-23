<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Bank Management System</h2>
    <div class="login-container">
        <form action="AdminLoginServlet" method="post">
            <h3>Admin Login</h3>
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
            <p><a href="customerLogin.jsp">Customer Login</a></p>
            <c: if test="${not empty param.error}">
                <p class="error">${param.error}</p>
            </c: if>
        </form>
    </div>
</body>
</html>
