import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cycleForm")
public class CookiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get user input and hidden field value
        String bikeType = request.getParameter("bike_type");
        String userName = request.getParameter("user_name");

        // Create a cookie to store the bike type
        Cookie bikeTypeCookie = new Cookie("bike_type", bikeType);
        bikeTypeCookie.setMaxAge(60 * 60 * 24); // Cookie will last for 1 day
        response.addCookie(bikeTypeCookie); // Add the cookie to the response

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

        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Try to get the bike type from cookies
        String bikeType = "Not selected";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("bike_type")) {
                    bikeType = cookie.getValue();
                }
            }
        }

        out.println("<html>");
        out.println("<head><title>Cycle Information</title>");
        out.println("<style>");
        out.println(
                "body { background-color: #ffccbc; font-family: Arial, sans-serif; text-align: center; padding: 50px; }");
        out.println("h1 { font-size: 36px; font-weight: bold; color: #333; }");
        out.println("p { font-size: 20px; color: #555; }");
        out.println("form { margin-top: 30px; }");
        out.println("input[type='text'] { padding: 8px 10px; font-size: 16px; }");
        out.println(
                "button { padding: 10px 15px; background-color: #ff7043; color: white; border: none; cursor: pointer; }");
        out.println("button:hover { background-color: #ff5722; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Show the cookie value or prompt the user to select a bike
        if (!bikeType.equals("Not selected")) {
            out.println("<h1>Welcome back! You previously selected a " + bikeType + " bike.</h1>");
        } else {
            out.println("<h1>Welcome to our Cycle Information Page</h1>");
            out.println("<p>Choose your bike type from the form below.</p>");
        }

        out.println("<form method='post' action='cycleForm'>");
        out.println("<input type='hidden' name='bike_type' value='road'>");
        out.println("<label for='user_name'>Your Name: </label>");
        out.println("<input type='text' id='user_name' name='user_name' required>");
        out.println("<button type='submit'>Submit</button>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
