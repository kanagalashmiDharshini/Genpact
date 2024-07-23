package bankSystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/ModifyAccount")
public class ModifyAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String account_no = request.getParameter("account_no");
        String full_name = request.getParameter("full_name");
        String address = request.getParameter("address");
        String mobile_no = request.getParameter("mobile_no");
        String email_id = request.getParameter("email_id");
        String account_type = request.getParameter("account_type");
        String dob = request.getParameter("dob");
        String id_proof = request.getParameter("id_proof");

        // Debugging output
        System.out.println("Received Parameters:");
        System.out.println("account_no: " + account_no);
        System.out.println("full_name: " + full_name);
        System.out.println("address: " + address);
        System.out.println("mobile_no: " + mobile_no);
        System.out.println("email_id: " + email_id);
        System.out.println("account_type: " + account_type);
        System.out.println("dob: " + dob);
        System.out.println("id_proof: " + id_proof);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");
                 PreparedStatement ps = con.prepareStatement("UPDATE customer SET full_name=?, address=?, mobile_no=?, email_id=?, account_type=?, dob=?, id_proof=? WHERE account_no=?")) {
                
                ps.setString(1, full_name);
                ps.setString(2, address);
                ps.setString(3, mobile_no);
                ps.setString(4, email_id);
                ps.setString(5, account_type);
                ps.setString(6, dob);
                ps.setString(7, id_proof);
                ps.setString(8, account_no);

                int result = ps.executeUpdate();

                out.println("<html><body>");
                if (result > 0) {
                    out.println("<p>Account details updated successfully</p>");
                } else {
                    out.println("<p>No customer found with account number: " + account_no + "</p>");
                }
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            out.println("<html><body><p>Error: " + e.getMessage() + "</p></body></html>");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            out.println("<html><body><p>Error: JDBC Driver not found</p></body></html>");
            e.printStackTrace();
        }
    }
}
