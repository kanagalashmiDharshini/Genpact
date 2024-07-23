<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Customer Dashboard</h2>
    <div class="container">
        <h3>Welcome ${sessionScope.full_name}</h3>
        <a href="deposit.jsp">Deposit</a>
        <a href="withdraw.jsp">Withdraw</a>
        <a href="CheckBalanceServlet">Check Balance</a>
        <a href="changePassword.jsp">Change Password</a>
        <a href="TransactionHistoryServlet">Transaction History</a>
       	<form action="LogoutServlet">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>

