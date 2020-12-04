package pl.coderslab.gov_app.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gov_app.interpellation.Interpellation;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class RoleService {

    RoleRepository roleRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
    public Optional<Role> getRole(Long id){
        return roleRepository.findById(id);
    }

}
