import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cyclecraft"; // Update with your MySQL database
                                                                                   // name
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "password"; // Replace with your MySQL password

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user details from the form
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Insert user details into the database
            String query = "INSERT INTO users (name, dob, username, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, dob);
            stmt.setString(3, username);
            stmt.setString(4, email);
            stmt.setString(5, password); // Ensure to hash this in a real application

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<h1>Registration Successful!</h1>");
                out.println("<p>Welcome to Cycle Craft, " + name + ".</p>");
            } else {
                out.println("<h1>Registration Failed.</h1>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Registration Failed. Please try again.</h1>");
        }
    }
}
