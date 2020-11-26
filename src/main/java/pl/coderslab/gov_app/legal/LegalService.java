package pl.coderslab.gov_app.legal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LegalService {
    private LegalRepository legalRepository;

    public List<Legal> getAllLegal(){
        return legalRepository.findAll();
    }

    public Optional<Legal> getLegal(Long id){
        return legalRepository.findById(id);
    }



}
