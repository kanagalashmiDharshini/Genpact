<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Change Password</h2>
    <div class="container">
        <form action="ChangePasswordServlet" method="post">
            <input type="password" name="current_password" placeholder="Current Password" required>
            <input type="password" name="new_password" placeholder="New Password" required>
            <button type="submit">Change Password</button>
            <c:if test="${not empty param.error}">
                <p class="error">${param.error}</p>
            </c:if>
            <c:if test="${not empty param.message}">
                <p class="success">${param.message}</p>
            </c:if>
        </form>
        <a href="customerDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>