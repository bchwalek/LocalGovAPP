package pl.coderslab.gov_app.interpellation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gov_app.councilman.Councilman;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class InterpellationService {

    InterpellationRepository interpellationRepository;

    public List<Interpellation> getAllInterpellation(){
        return interpellationRepository.findAll();
    }

    public Optional<Interpellation> getInerpellation(Long id){
        return interpellationRepository.findById(id);
    }

    public void addInterpellation(Interpellation interpellation){
        interpellationRepository.save(interpellation);
    }

    public void deleteInterpellation (Long id){
        interpellationRepository.deleteById(id);
    }

    public void updateInterpellation(Interpellation interpellation) {
    }

    public List<Interpellation> getInterpellationByCouncilId(Long id){
      return interpellationRepository.findByCouncilman_Id(id);
    }

}
