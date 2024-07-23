<!DOCTYPE html>
<html>
<head>
    <title>Check Balance</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Check Balance</h2>
    <div class="container">
        <form action="CheckBalanceServlet" method="get">
            <% 
                Double balance = (Double) request.getAttribute("balance");
                String error = (String) request.getAttribute("error");
            %>
            <% if (balance != null) { %>
                <p>Your current balance is: $<%= balance %></p>
            <% } else if (error != null) { %>
                <p class="error"><%= error %></p>
            <% } %>
            <a href="customerDashboard.jsp">Back to Dashboard</a>
        </form>
    </div>
</body>
</html>
