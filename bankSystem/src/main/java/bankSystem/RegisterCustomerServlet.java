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

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String Acc_no = request.getParameter(generateAccountNo());
        String fullName = request.getParameter("full_name");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobile_no");
        String emailId = request.getParameter("email_id");
        String accountType = request.getParameter("account_type");
        String initialBalance = request.getParameter("initial_balance");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("id_proof");
        String password = "2019"; // Temporary password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");

            PreparedStatement ps = con.prepareStatement("INSERT INTO customer (account_no, full_name, address, mobile_no, email_id, account_type, initial_balance, dob, id_proof, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, Acc_no);
            ps.setString(2, fullName);
            ps.setString(3, address);
            ps.setString(4, mobileNo);
            ps.setString(5, emailId);
            ps.setString(6, accountType);
            ps.setString(7, initialBalance);
            ps.setString(8, dob);
            ps.setString(9, idProof);
            ps.setString(10, password);
            ps.executeUpdate();

            response.sendRedirect("adminDashboard.jsp?success=Customer registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String generateAccountNo() {
        return String.valueOf((long) (Math.random() * 10000000000L));
    }
}
