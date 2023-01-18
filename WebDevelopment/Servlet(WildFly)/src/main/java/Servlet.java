import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet{
    @EJB
    JavaBean javaBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=windows-1251");
        try {
            PrintWriter writer = resp.getWriter();
            String item = req.getParameter("item");
            if(!item.equals("")) {
                Shop user=new Shop();
                user.setItem(item);
                javaBean.saveShop(user);
            }
            List<?> items = javaBean.getListItems();
            writer.println("<ul>Магазин</ul>");
            for(Object itemFromList : items){
                writer.println("<li>item: " + itemFromList + "</li>");
            }
            writer.println("<button><a href='index.jsp'>Добавить новый товар</a></button>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=windows-1251");
        try {
            PrintWriter writer = resp.getWriter();
            String item = req.getParameter("item");
            if(!item.equals("")) {
                Shop user=new Shop();
                user.setItem(item);
                javaBean.saveShop(user);
            }
            List<?> items = javaBean.getListItems();
            writer.println("<ul>Магазин</ul>");
            for(Object itemFromList : items){
                writer.println("<li>item: " + itemFromList + "</li>");
            }
            writer.println("<button><a href='index.jsp'>Добавить новый товар</a></button>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}