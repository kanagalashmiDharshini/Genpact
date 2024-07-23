<!DOCTYPE html>
<html>
<head>
    <title>Withdraw</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Withdraw</h2>
    <div class="container">
        <form action="WithdrawServlet" method="post">
            <input type="number" name="amount" placeholder="Amount" required>
            <button type="submit">Withdraw</button>
            <c: if test="${not empty param.message}">
                <p class="success">${param.message}</p>
            </c: if>
            <c: if test="${not empty param.error}">
                <p class="error">${param.error}</p>
            </c: if>
        </form>
    </div>
</body>
</html>
