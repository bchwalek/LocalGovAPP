package pl.coderslab.gov_app.councilman;


import pl.coderslab.gov_app.role.Role;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Councilman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię nie może być puste")
    private String firstName;

    @NotBlank(message = "Nazwisko nie może być puste")
    private String lastName;

    @NotBlank(message = "Dodaj opis!")
    private String description;

    @Email(message = "Nieprawidlowy email")
    @NotBlank(message = "Podaj email")
    private String email;

    private Boolean isDelete;

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    //    @Size(min=2, max=10, message = "Hasło musi mieć conajmniej 2 i maksymalnie 10 znaków")
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @NotNull(message = "Wybierz Poziom Dostępu")

    @OneToOne(cascade = CascadeType.PERSIST)
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

}
