package pl.coderslab.gov_app.superadmin;

import pl.coderslab.gov_app.role.Role;

import javax.persistence.*;

@Entity
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }



    public String getPassword() {
        return password;
    }




    @OneToOne
    Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

}
