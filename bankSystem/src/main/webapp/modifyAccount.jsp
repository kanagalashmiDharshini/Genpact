<!DOCTYPE html>
<html>
<head>
    <title>Modify Account</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    
</head>
<body>
   <h2>Modify Account</h2>
    <form action="ModifyAccount" method="POST">
        <label for="account_no">Account Number:</label>
        <input type="text" id="account_no" name="account_no"><br>

        <label for="full_name">Full Name:</label>
        <input type="text" id="full_name" name="full_name"><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address"><br>

        <label for="mobile_no">Mobile Number:</label>
        <input type="text" id="mobile_no" name="mobile_no"><br>

        <label for="email_id">Email ID:</label>
        <input type="text" id="email_id" name="email_id"><br>

        <label for="account_type">Account Type:</label>
        <input type="text" id="account_type" name="account_type"><br>

        <label for="dob">Date of Birth:</label>
        <input type="text" id="dob" name="dob"><br>

        <label for="id_proof">ID Proof:</label>
        <input type="text" id="id_proof" name="id_proof"><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
