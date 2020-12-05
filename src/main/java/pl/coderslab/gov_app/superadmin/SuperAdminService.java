package pl.coderslab.gov_app.superadmin;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SuperAdminService {


    private final SuperAdminRepository superAdminRepository;

    public SuperAdminService(SuperAdminRepository superAdminRepository) {
        this.superAdminRepository = superAdminRepository;
    }

    public List<SuperAdmin> getAllSuperAdmin(){
        return superAdminRepository.findAll();
    }

    public Optional<SuperAdmin> getSuperAdmin(Long id){
        return superAdminRepository.findById(id);
    }

    public void addSuperAdmin(SuperAdmin superAdmin){
        superAdminRepository.save(superAdmin);
    }

    public void deleteSuperAdmin (Long id){
        superAdminRepository.deleteById(id);
    }

    public void updateCouncilman(SuperAdmin superAdmin) {

    }

    public SuperAdmin findByEmail (String email){
        return superAdminRepository.findByEmail(email);
    }


}
