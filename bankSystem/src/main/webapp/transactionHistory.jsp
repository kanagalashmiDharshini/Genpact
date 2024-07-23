<%@ page import="java.util.List" %>
<%@ page import="bankSystem.Transaction" %>

<!DOCTYPE html>
<html>
<head>
    <title>Transaction History</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Transaction History</h2>
    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Account No</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
                    if (transactions != null && !transactions.isEmpty()) {
                        for (Transaction transaction : transactions) {
                %>
                <tr>
                    <td><%= transaction.getTransactionId() %></td>
                    <td><%= transaction.getAccountNo() %></td>
                    <td><%= transaction.getType() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getDate() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No transactions found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <a href="customerDashboard.jsp">Back to Dashboard</a>
</body>
</html>
