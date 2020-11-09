package pl.coderslab.gov_app.interpellation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.gov_app.councilman.Councilman;

import java.util.List;


public interface InterpellationRepository extends JpaRepository<Interpellation, Long> {

    List<Interpellation> findByCouncilman_Id(Long id);

}


