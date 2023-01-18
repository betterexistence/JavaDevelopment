import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Validation")
public class Validation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        String item = req.getParameter("item");
        new ActiveMQ.ProducerMQ(item);
        req.getSession().setAttribute("item", item);
        try {
            resp.sendRedirect("/WildFlyServlet/Servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
