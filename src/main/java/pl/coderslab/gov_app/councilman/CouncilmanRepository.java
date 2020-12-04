package pl.coderslab.gov_app.councilman;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CouncilmanRepository extends JpaRepository<Councilman, Long> {

//    Councilman findByFirstName(String firstName);
    Councilman findByEmail(String email);
}
