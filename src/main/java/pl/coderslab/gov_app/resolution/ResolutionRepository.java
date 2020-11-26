package pl.coderslab.gov_app.resolution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ResolutionRepository extends JpaRepository <Resolution, Long> {

    @Query("SELECT r FROM Resolution r WHERE r.sessionOrder.id =?1")
    List<Resolution> findResolutionByOrderId(Long id);
}
