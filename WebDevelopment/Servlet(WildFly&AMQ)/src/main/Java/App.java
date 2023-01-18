import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/Servlet")
public class App extends HttpServlet{
    @EJB
    JavaBean javaBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
//        Shop product = new Shop();
//        String productName = req.getParameter("item");
//        product.setItem(productName);
//        javaBean.saveShop(product);
        PrintWriter writer = resp.getWriter();
        writer.println("<ul>Магазин</ul>");
        for(Object itemFromList : javaBean.getListItems()){
            writer.println("<li>item: " + itemFromList + "</li>");
        }
        writer.println("<button><a href='index.jsp'>Добавить новый товар</a></button>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
