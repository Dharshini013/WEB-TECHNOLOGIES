import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cycleDescription")
public class CycleSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        String message;
        String bodyStyle = "background-color: #a5d6a7; font-family: 'Verdana', sans-serif;"; // Light green background and new font
        
        // Check if session attribute "visited" exists
        if (session.isNew() || session.getAttribute("visited") == null) {
            message = "Welcome to the Mountain Cycle Website!";
            bodyStyle = "background-color: #81c784; font-family: 'Verdana', sans-serif;"; // Change background color for first-time visitors
            session.setAttribute("visited", true);  // Set session attribute on first visit
        } else {
            message = "Welcome back to the Mountain Cycle Website!";
        }

        // Use URL rewriting to pass session info in the URL
        String rewrittenURL = response.encodeURL("cycleDescription");

        // Display the message and mountain cycle description
        out.println("<html>");
        out.println("<head><title>Mountain Cycle Description</title>");
        out.println("<style> .fade-in { animation: fadeIn 2s ease-in-out; } @keyframes fadeIn { 0% { opacity: 0; } 100% { opacity: 1; } } </style>");
        out.println("</head>");
        out.println("<body style='" + bodyStyle + "'>");
        out.println("<h1 style='font-size: 40px; font-weight: bold; text-align: center;'>" + message + "</h1>");
        
        // Mountain Cycle Description
        out.println("<h2 style='text-align: center;'>Mountain Cycle</h2>");
        out.println("<p style='text-align: center;'>The mountain cycle is designed for tough terrains, such as rocky paths, hills, and dirt tracks. It features strong tires, durable suspension, and a lightweight frame, making it perfect for adventurous riders who love to explore nature. Ideal for adults and teens.</p>");
        
        // Provide the URL with session ID (URL Rewriting)
        out.println("<p style='text-align: center;'>Click the link below to revisit this page with the session ID:</p>");
        out.println("<a href='" + rewrittenURL + "' style='font-size: 18px; text-decoration: none; color: darkblue;'>Revisit Mountain Cycle Information</a>");
        
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}
