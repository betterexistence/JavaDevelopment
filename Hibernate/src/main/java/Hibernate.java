import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class Hibernate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        TableImpl impl = new TableImpl();
        impl.read(session);
        session.close();
        sessionFactory.close();
    }
}

interface TableActions{
    void create(Session session);
    void read(Session session);
    void update(Session session);
    void delete(Session session);
}

class TableImpl implements TableActions{

    @Override
    public void create(Session session) {
        TestUser testUser = new TestUser();
        testUser.setName("Omen");
        testUser.setAge(45);
        session.beginTransaction();
        session.save(testUser);
        session.getTransaction().commit();
        System.out.println(testUser);
    }

    @Override
    public void read(Session session) {
        Collection testUsers = session.createCriteria(TestUser.class).list();
        for (Object user : testUsers) {
            TestUser testUser = (TestUser) user;
            System.out.println(testUser.getId() + "\t" + testUser.getName() + "\t" + testUser.getAge());
        }
    }

    @Override
    public void update(Session session) {
        int id = 1;
        TestUser testUser = (TestUser) session.get(TestUser.class, new Integer(id));
        testUser.setAge(12);
        session.beginTransaction();
        session.saveOrUpdate(testUser);
        session.getTransaction().commit();
        System.out.println("succesfully");
    }

    @Override
    public void delete(Session session) {
        int id = 1;
        TestUser testUser = (TestUser) session.get(TestUser.class, new Integer(id));
        session.beginTransaction();
        session.delete(testUser);
        session.getTransaction().commit();
        System.out.println("void delete testUser = " + id);
    }
}

@Entity
@Table(name = "testusers")
class TestUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public TestUser(){
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}