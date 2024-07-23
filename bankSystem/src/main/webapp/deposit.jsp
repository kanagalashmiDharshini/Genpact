<!DOCTYPE html>
<html>
<head>
    <title>Deposit</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Deposit</h2>
    <div class="container">
        <form action="DepositServlet" method="post">
            <input type="number" name="amount" placeholder="Amount" required>
            <button type="submit">Deposit</button>
            <c: if test="${not empty param.message}">
                <p class="success">${param.message}</p>
            </c: if>
        </form>
    </div>
</body>
</html>

