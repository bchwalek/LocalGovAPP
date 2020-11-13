package pl.coderslab.gov_app.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class RoleService {

    RoleRepository roleRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

}
