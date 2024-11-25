import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cycleForm")
public class CycleRoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data and hidden field value
        String bikeType = request.getParameter("bike_type");
        String userName = request.getParameter("user_name");

        out.println("<html>");
        out.println("<head><title>Road Bike Information</title>");
        out.println("<style>");
        out.println(
                "body { background-color: #ffccbc; font-family: Arial, sans-serif; text-align: center; padding: 50px; }");
        out.println("h1 { font-size: 35px; font-weight: bold; color: #333; }");
        out.println("h2 { font-size: 30px; color: #444; }");
        out.println("p { font-size: 18px; color: #555; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Display bike type and a thank you message
        out.println("<h1>Thank You, " + userName + "</h1>");
        out.println("<h2>You've chosen a " + bikeType + " bike!</h2>");
        out.println(
                "<p>A road bike is designed for speed on paved roads, with lightweight frames, smooth tires, and a low aerodynamic profile.</p>");

        // Do not display the form again
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
