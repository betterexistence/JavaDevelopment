import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class JavaBean {

    @PersistenceContext(unitName = "myUnit")
    EntityManager entityManager;

    public void saveShop(Shop shop){
        entityManager.persist(shop);
    }

    public List<?> getListItems(){
        List<?> items = entityManager.createNativeQuery("SELECT item FROM shop").getResultList();
        return items;
    }
}