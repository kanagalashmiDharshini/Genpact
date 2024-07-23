package bankSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("account_no");
        String amount = request.getParameter("amount");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            PreparedStatement ps = con.prepareStatement("UPDATE customer SET initial_balance = initial_balance + ? WHERE account_no = ?");
            ps.setString(1, amount);
            ps.setString(2, accountNo);
            ps.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("INSERT INTO transactions (account_no, type, amount) VALUES (?, 'Deposit', ?)");
            ps2.setString(1, accountNo);
            ps2.setString(2, amount);
            ps2.executeUpdate();

            response.sendRedirect("customerDashboard.jsp?message=Deposit successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
