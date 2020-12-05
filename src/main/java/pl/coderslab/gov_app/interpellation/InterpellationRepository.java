package pl.coderslab.gov_app.interpellation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface InterpellationRepository extends JpaRepository<Interpellation, Long> {

    List<Interpellation> findByCouncilman_Id(Long id);

    @Query("SELECT i FROM Interpellation i WHERE i.isAnswer=?1")
    List<Interpellation> intIsAnswer(Boolean answer);

}


