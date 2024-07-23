<!DOCTYPE html>
<html>
<head>
    <title>Customer register</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Customer register</h2>
    <div class="container">
        <h3>Register Customer</h3>
        <form action="RegisterCustomerServlet" method="post">
            <input type="text" name="full_name" placeholder="Full Name" required>
            <input type="text" name="address" placeholder="Address" required>
            <input type="text" name="mobile_no" placeholder="Mobile No" required>
            <input type="email" name="email_id" placeholder="Email ID" required>
            <select name="account_type" required>
                <option value="Saving">Saving Account</option>
                <option value="Current">Current Account</option>
            </select>
            <input type="number" name="initial_balance" placeholder="Initial Balance" min="1000" Amount required>
            <input type="date" name="dob" required>
            <input type="text" name="id_proof" placeholder="ID Proof" required>
            <button type="submit">Register</button>
            <c: if test="${not empty param.success}">
                <p class="success">${param.success}</p>
            </c: if>
        </form>
    </div>
</body>
</html>

