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
        String bodyStyle = "background-color: skyblue;"; // default background color

        // Check if session attribute "visited" exists
        if (session.isNew() || session.getAttribute("visited") == null) {
            message = "Welcome to the Cycle Website!";
            bodyStyle = "background-color: lightgreen;"; // Change background color for first-time visitors
            session.setAttribute("visited", true); // Set session attribute on first visit
        } else {
            message = "Welcome back to the Cycle Website!";
        }

        // Display the message and kids cycle description
        out.println("<html>");
        out.println("<head><title>Cycle Description</title>");
        out.println(
                "<style> .fade-in { animation: fadeIn 2s ease-in-out; } @keyframes fadeIn { 0% { opacity: 0; } 100% { opacity: 1; } } </style>");
        out.println("</head>");
        out.println("<body style='" + bodyStyle + "'>");
        out.println("<h1 style='font-size: 36px; font-weight: bold;'>" + message + "</h1>");

        // Kids Cycle Description
        out.println("<h2>Kids Cycle</h2>");
        out.println(
                "<p>The kids' cycle is designed with safety and comfort in mind. It is lightweight, comes in fun colors, and is built to help children develop their cycling skills. Ideal for kids between the ages of 3 and 10.</p>");

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
