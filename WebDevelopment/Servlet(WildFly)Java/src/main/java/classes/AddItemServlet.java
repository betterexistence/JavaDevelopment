package classes;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {

    @EJB
    JavaBean javaBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String item = req.getParameter("item");
        Shop shop = new Shop();
        shop.setItem(item);
        javaBean.saveShop(shop);
        getServletContext().getRequestDispatcher("/Servlet").forward(req,resp);
    }
}
