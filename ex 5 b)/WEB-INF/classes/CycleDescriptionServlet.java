import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cycleDescription")
public class CycleDescriptionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Cycle Description</title></head>");
        out.println("<body style='background-color: skyblue;'>");
        out.println("<h1>About Cycles</h1>");
        out.println(
                "<p>Cycles are a popular mode of transportation and exercise. They come in various types and brands, each designed for different terrains and preferences. From mountain bikes to road bikes, cycling is an eco-friendly and healthy way to get around.</p>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
