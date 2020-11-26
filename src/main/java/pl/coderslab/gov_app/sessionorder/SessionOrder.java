package pl.coderslab.gov_app.sessionorder;

import pl.coderslab.gov_app.legal.Legal;
import pl.coderslab.gov_app.sessionelem.Sessionelem;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SessionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String date;
    private String time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ManyToMany
    @JoinTable(name = "order_elements",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "elem_id"))

    private List<Sessionelem> elems = new ArrayList<>();

    public List<Sessionelem> getElems() {
        return this.elems;
    }

    public void setElems(List<Sessionelem> elems) {
        this.elems = elems;
    }

    @OneToOne
    private Legal legal;

    public Legal getLegal() {
        return legal;
    }

    public void setLegal(Legal legal) {
        this.legal = legal;
    }
}
