package pl.coderslab.gov_app.councilman;

import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class CouncilmanService{

    private CouncilmanRepository councilmanRepository;

    public List<Councilman> getAllCouncilman(){
        return councilmanRepository.findAll();
    }

    public Optional<Councilman> getCouncilman(Long id){
        return councilmanRepository.findById(id);
    }

    public void addCoucilman(Councilman councilman){
        councilmanRepository.save(councilman);
    }

    public void deleteCouncilman (Long id){
        councilmanRepository.deleteById(id);
    }

    public void updateCouncilman(Councilman councilman){

    }

}
