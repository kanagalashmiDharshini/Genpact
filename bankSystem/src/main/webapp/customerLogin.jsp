<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Customer Login</h2>
    <div class="login-container">
        <form action="CustomerLoginServlet" method="post">
            <input type="text" name="account_no" placeholder="Account Number" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
            <c: if test="${not empty param.error}">
                <p class="error">${param.error}</p>
            </c: if>
        </form>
    </div>
</body>
</html>
