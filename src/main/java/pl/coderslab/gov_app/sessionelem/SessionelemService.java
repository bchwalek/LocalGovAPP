package pl.coderslab.gov_app.sessionelem;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gov_app.councilman.Councilman;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class SessionelemService {
    SessionelemRepository sessionelemRepository;

    public List<Sessionelem> getAllElem(){
        return sessionelemRepository.findAll();
    }

    public void addElem(Sessionelem sessionelem){
        sessionelemRepository.save(sessionelem);
    }

    public Optional<Sessionelem> getElem(Long id){
        return sessionelemRepository.findById(id);
    }

    public List<Sessionelem> getSessionElemByOrderID(Long id){
       return sessionelemRepository.findBySessionOrderID(id);
    }
}
