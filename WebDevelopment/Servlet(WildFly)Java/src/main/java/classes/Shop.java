package classes;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public
class Shop {
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    private int id;
    private String item;

    public Shop(){
    }

    public Shop(int id, String item){
        this.id = id;
        this.item = item;
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