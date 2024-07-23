package bankSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("account_no");

        if (accountNo == null) {
            response.sendRedirect("customerLogin.jsp?error=Please login first");
            return;
        }

        List<Transaction> transactions = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM transactions WHERE account_no = ?");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountNo(rs.getInt("account_no"));
                transaction.setType(rs.getString("type"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setDate(rs.getTimestamp("date"));
                transactions.add(transaction);
            }

            if (transactions.isEmpty()) {
                System.out.println("No transactions found for account number: " + accountNo);
            }

            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

