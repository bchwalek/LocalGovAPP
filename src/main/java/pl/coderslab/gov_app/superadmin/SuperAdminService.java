package pl.coderslab.gov_app.superadmin;

import pl.coderslab.gov_app.councilman.Councilman;
import pl.coderslab.gov_app.councilman.CouncilmanRepository;

import java.util.List;
import java.util.Optional;


public class SuperAdminService {

    private SuperAdminRepository superAdminRepository;

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
}
