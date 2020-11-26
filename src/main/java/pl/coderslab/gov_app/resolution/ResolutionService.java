package pl.coderslab.gov_app.resolution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ResolutionService {
    ResolutionRepository resolutionRepository;

    public List<Resolution> getAllResolution(){
        return resolutionRepository.findAll();
    }

    public Optional<Resolution> getResolution(Long id){
        return resolutionRepository.findById(id);
    }

    public void addResolution(Resolution resolution){
        resolutionRepository.save(resolution);
    }

    public void deleteResolution (Long id){
        resolutionRepository.deleteById(id);
    }

    public void updateResolution(Resolution resolution) {

    }

    public List<Resolution> getResolutionByOrderId(Long id){
       return resolutionRepository.findResolutionByOrderId(id);
    }
}
