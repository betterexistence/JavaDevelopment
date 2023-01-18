import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Validation")
public class Validation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String item = req.getParameter("item");
            //new MessageSender("queue", item);
            resp.sendRedirect("/WildFlyServlet/Servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
