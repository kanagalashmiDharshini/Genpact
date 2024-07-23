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

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");

        if (accountNo == null || accountNo.isEmpty()) {
            response.sendRedirect("customerDashboard.jsp?error=Account number is required");
            return;
        }

        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            ps1 = con.prepareStatement("DELETE FROM balance WHERE account_no = ?");
            ps1.setString(1, accountNo);
            ps1.executeUpdate();

            ps2 = con.prepareStatement("DELETE FROM transactions WHERE account_no = ?");
            ps2.setString(1, accountNo);
            ps2.executeUpdate();

            ps3 = con.prepareStatement("DELETE FROM customer WHERE account_no = ?");
            ps3.setString(1, accountNo);
            ps3.executeUpdate();

            response.sendRedirect("login.jsp?message=Account deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = e.getMessage();
            response.sendRedirect("customerDashboard.jsp?error=" + java.net.URLEncoder.encode("Unable to delete account: " + errorMessage, "UTF-8"));
        } finally {
            try { if (ps1 != null) ps1.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (ps2 != null) ps2.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (ps3 != null) ps3.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}