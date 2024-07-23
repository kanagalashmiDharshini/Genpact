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

@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("account_no");

        if (accountNo == null) {
            response.sendRedirect("customerLogin.jsp?error=Please login first");
            return;
        }

        Double balance = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            PreparedStatement ps = con.prepareStatement("SELECT balance FROM balance WHERE account_no = ?");
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("balance");
            } else {
                request.setAttribute("error", "No balance information available.");
            }

            request.setAttribute("balance", balance);
            request.getRequestDispatcher("checkBalance.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while retrieving balance information.");
            request.getRequestDispatcher("checkBalance.jsp").forward(request, response);
        }
    }
}

