package pl.coderslab.gov_app.councilman;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class CouncilmanService implements UserDetailsService {

    private CouncilmanRepository councilmanRepository;


    public CouncilmanService(CouncilmanRepository councilmanRepository) {
        this.councilmanRepository = councilmanRepository;
    }

    public List<Councilman> getAllCouncilman(){
        return councilmanRepository.findAll();
    }

    public Optional<Councilman> getCouncilman(Long id){
        return councilmanRepository.findById(id);
    }

    public void addCoucilman(Councilman councilman){
        councilmanRepository.save(councilman);
    }

    public Councilman getByname (String name) {
        return councilmanRepository.findByEmail(name);
    }

    public void deleteCouncilman (Long id){
        councilmanRepository.deleteById(id);
    }

    public void updateCouncilman(Councilman councilman){
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return  councilmanRepository.findByEmail(s);
    }
}
