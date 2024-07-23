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

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNo = (String) session.getAttribute("account_no");
        String currentPassword = request.getParameter("current_password");
        String newPassword = request.getParameter("new_password");

        if (accountNo == null) {
            response.sendRedirect("customerLogin.jsp?error=Please login first");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            PreparedStatement ps = con.prepareStatement("SELECT password FROM customer WHERE account_no=? AND password=?");
            ps.setString(1, accountNo);
            ps.setString(2, currentPassword);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PreparedStatement ps2 = con.prepareStatement("UPDATE customer SET password=? WHERE account_no=?");
                ps2.setString(1, newPassword);
                ps2.setString(2, accountNo);
                ps2.executeUpdate();
                response.sendRedirect("customerDashboard.jsp?message=Password changed successfully");
            } else {
                response.sendRedirect("changePassword.jsp?error=Current password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("changePassword.jsp?error=An error occurred while changing the password");
        }
    }
}