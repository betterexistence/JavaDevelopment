import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html;charset=windows-1251");
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        TableImpl table = new TableImpl();
        try (PrintWriter writer = resp.getWriter()) {
            String item = req.getParameter("item");
            if(!item.equals("") || item != null) table.create(session,item);
            List shopList = session.createCriteria(Shop.class).list();
            writer.println("<ul>Магазин</ul>");
            for(Object shopListItem : shopList){
                Shop shopItem = (Shop) shopListItem;
                writer.println("<li>item: " + shopItem.getItem() + "</li>");
            }
            writer.println("<button><a href='../index.jsp'>Добавить новый товар</a></button>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=windows-1251");
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        TableImpl table = new TableImpl();
        try (PrintWriter writer = resp.getWriter()) {
            String item = req.getParameter("item");
            if(!item.equals("")) table.create(session,item);
            List shopList = session.createCriteria(Shop.class).list();
            writer.println("<ul>Магазин</ul>");
            for(Object shopListItem : shopList){
                Shop shopItem = (Shop) shopListItem;
                writer.println("<li>item: " + shopItem.getItem() + "</li>");
            }
            writer.println("<button><a href='../index.jsp'>Добавить новый товар</a></button>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}