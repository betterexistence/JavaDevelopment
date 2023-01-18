import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet{

    @EJB
    JavaBean javaBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=windows-1251");
        ActiveMQ.ConsumerMQ consumerMQ = new ActiveMQ.ConsumerMQ();
        if(consumerMQ.outMsg.equals("printList")) printList(req,resp);
        else printError(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    private void printList(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html;charset=windows-1251");
        try {
            PrintWriter writer = resp.getWriter();
            String item = String.valueOf(req.getSession().getAttribute("item"));
            Shop user = new Shop();
            user.setItem(item);
            javaBean.saveShop(user);
            writer.println("<ul>Магазин</ul>");
            for(Object itemFromList : javaBean.getListItems()){
                writer.println("<li>item: " + itemFromList + "</li>");
            }
            writer.println("<button><a href='index.jsp'>Добавить новый товар</a></button>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printError(HttpServletResponse resp){
        resp.setContentType("text/html;charset=windows-1251");
        try {
            PrintWriter writer = resp.getWriter();
            writer.println("<p>Некорректное название продукта</p>");
            writer.println("<button><a href='index.jsp'>Добавить новый товар</a></button>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}