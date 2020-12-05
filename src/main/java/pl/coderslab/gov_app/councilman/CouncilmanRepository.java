package pl.coderslab.gov_app.councilman;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CouncilmanRepository extends JpaRepository<Councilman, Long> {

    Councilman findByEmail(String email);

    @Query("SELECT c FROM Councilman c WHERE c.isDelete=?1")
    List<Councilman> isDelete (Boolean delete);
}
