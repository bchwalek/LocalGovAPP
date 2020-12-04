package pl.coderslab.gov_app.superadmin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {

    SuperAdmin findByEmail(String email);

}
