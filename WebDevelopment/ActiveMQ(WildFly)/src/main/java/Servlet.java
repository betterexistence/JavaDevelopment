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

//    @Resource(lookup = "java:/ConnectionFactory")
//    ConnectionFactory cf;
//    @Resource(lookup = "java:jboss/exported/jms/queue/serverQueue")
//    private Queue queue;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("asdasd");
    }

        //        try {
//            MessageReceiver messageReceiver = new MessageReceiver("queue");
//            if(messageReceiver.getItem() != "!@#$"){
//                Shop user = new Shop();
//                user.setItem(messageReceiver.getItem());
//                javaBean.saveShop(user);
//            }
//            PrintWriter writer = resp.getWriter();
//            writer.println("<ul>Магазин</ul>");
//            for(Object itemFromList : javaBean.getListItems()){
//                writer.println("<li>item: " + itemFromList + "</li>");
//            }
//            writer.println("<button><a href='index.jsp'>Добавить новый товар</a></button>");
//            writer.close();
//        } catch (IOException | JMSException e) {
//            e.printStackTrace();
//        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}