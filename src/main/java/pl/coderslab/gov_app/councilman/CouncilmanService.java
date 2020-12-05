package pl.coderslab.gov_app.councilman;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class CouncilmanService {

    private final CouncilmanRepository councilmanRepository;


    public CouncilmanService(CouncilmanRepository councilmanRepository) {
        this.councilmanRepository = councilmanRepository;
    }

    public List<Councilman> getAllCouncilman() {
        return councilmanRepository.findAll();
    }

    public List<Councilman> getCouncilmanIsDelete (Boolean delete) {return councilmanRepository.isDelete(delete);}

    public Optional<Councilman> getCouncilman(Long id) {
        return councilmanRepository.findById(id);
    }

    public void addCoucilman(Councilman councilman) {
        councilmanRepository.save(councilman);
    }

    public Councilman getByname(String name) {
        return councilmanRepository.findByEmail(name);
    }

    public void deleteCouncilman(Long id) {
        councilmanRepository.deleteById(id);
    }

    public void updateCouncilman(Councilman councilman) {
    }

    public Councilman getByEmail(String email) {
        return councilmanRepository.findByEmail(email);
    }

}
