package pl.coderslab.gov_app.interpellation;

import pl.coderslab.gov_app.councilman.Councilman;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Interpellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private String description;
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String date(){
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat ("yyyy-MM-dd");
        return formatDate.format(date);
    }

    @ManyToOne
    private Councilman councilman;

    public void setCouncilman(Councilman councilman) {
        this.councilman = councilman;
    }

    public Councilman getCouncilman() {
        return councilman;
    }
}
