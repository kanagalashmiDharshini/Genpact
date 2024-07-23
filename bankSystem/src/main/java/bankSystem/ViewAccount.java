package bankSystem;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/ViewAccount")
public class ViewAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String account_no = request.getParameter("account_no");

        // Debugging output
        System.out.println("Received account_no: " + account_no);

        if (account_no == null || account_no.trim().isEmpty()) {
            out.println("<html><body><p>Error: Account number is missing or empty</p></body></html>");
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "admin");
                 PreparedStatement ps = con.prepareStatement("SELECT * FROM customer WHERE account_no = ?")) {

                ps.setString(1, account_no.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    out.println("<html><body>");
                    if (rs.next()) {
                        out.println("<h2>Customer Details</h2>");
                        out.println("<p>account_no: " + rs.getString("account_no") + "</p>");
                        out.println("<p>full_name: " + rs.getString("full_name") + "</p>");
                        out.println("<p>address: " + rs.getString("address") + "</p>");
                        out.println("<p>mobile_no: " + rs.getString("mobile_no") + "</p>");
                        out.println("<p>email_id: " + rs.getString("email_id") + "</p>");
                        out.println("<p>account_type: " + rs.getString("account_type") + "</p>");
                        out.println("<p>dob: " + rs.getString("dob") + "</p>");
                    } else {
                        out.println("<p>Error: No account found with the given account number</p>");
                    }
                    out.println("</body></html>");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<html><body><p>Error: Unable to load database driver</p></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><p>Error: Database connection or query execution failed</p></body></html>");
        }
    }
}
