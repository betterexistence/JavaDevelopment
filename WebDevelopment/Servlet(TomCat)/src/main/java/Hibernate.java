import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

class TableImpl{

    public void create(Session session, String item){
        Shop shop = new Shop();
        shop.setItem(item);
        session.beginTransaction();
        session.save(shop);
        session.getTransaction().commit();
    }

    public static void read(Session session){
        Collection shops = session.createCriteria(Shop.class).list();
        for (Object shop : shops) {
            Shop item = (Shop) shop;
            System.out.println(item.getId() + "\t" + item.getItem());
        }
    }
}

@Entity
@Table(name = "shop")
class Shop implements Serializable {

    private static final long serialVersionUID = 1L;
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    private int id;
    @Column(name = "item")
    private String item;

    public Shop(){
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }
    public void setItem(String name) {
        this.item = name;
    }
}