<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Account</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Enter Account Number to View Account Details</h2>
    <form action="ViewAccount" method="get">
        <label for="account_no">Account Number:</label>
        <input type="text" id="account_no" name="account_no" required>
        <input type="submit" value="View Account">
    </form>
</body>
</html>
