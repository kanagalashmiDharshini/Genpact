<!DOCTYPE html>
<html>
<head>
    <title>Delete Customer</title>
</head>
<body>
    <h2>Delete Customer Account</h2>
    <form action="DeleteAccountServlet" method="post">
        <label for="accountNo">Account No:</label>
        <input type="text" id="accountNo" name="account_no" required><br><br>
        <input type="submit" value="Delete">
    </form>
</body>
</html>