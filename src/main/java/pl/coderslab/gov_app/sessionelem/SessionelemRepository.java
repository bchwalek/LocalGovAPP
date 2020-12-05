package pl.coderslab.gov_app.sessionelem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionelemRepository extends JpaRepository<Sessionelem, Long> {

    @Query(value = "SELECT * FROM sessionelem JOIN order_elements ON sessionelem.id = order_elements.elem_id WHERE order_elements.order_id= :id", nativeQuery=true)
    List<Sessionelem> findBySessionOrderID(@Param("id") Long id);

}
