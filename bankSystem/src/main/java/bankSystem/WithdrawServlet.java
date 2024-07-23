package bankSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("account_no");
        String amount = request.getParameter("amount");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            PreparedStatement ps1 = con.prepareStatement("SELECT initial_balance FROM customer WHERE account_no = ?");
            ps1.setString(1, accountNo);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("initial_balance");
                double withdrawAmount = Double.parseDouble(amount);
                if (balance >= withdrawAmount) {
                    PreparedStatement ps2 = con.prepareStatement("UPDATE customer SET initial_balance = initial_balance - ? WHERE account_no = ?");
                    ps2.setString(1, amount);
                    ps2.setString(2, accountNo);
                    ps2.executeUpdate();

                    PreparedStatement ps3 = con.prepareStatement("INSERT INTO transactions (account_no, type, amount) VALUES (?, 'Withdraw', ?)");
                    ps3.setString(1, accountNo);
                    ps3.setString(2, amount);
                    ps3.executeUpdate();

                    response.sendRedirect("customerDashboard.jsp?message=Withdraw successful");
                } else {
                    response.sendRedirect("customerDashboard.jsp?error=Insufficient balance");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

