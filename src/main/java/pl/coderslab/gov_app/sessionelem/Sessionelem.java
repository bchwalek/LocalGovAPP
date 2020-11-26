package pl.coderslab.gov_app.sessionelem;

import pl.coderslab.gov_app.sessionorder.SessionOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sessionelem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "elems")
    private List<SessionOrder> orders = new ArrayList<>();

    public List<SessionOrder> getOrders() {
        return this.orders;
    }

    public void setOrders(List<SessionOrder> orders) {
        this.orders = orders;
    }

}
