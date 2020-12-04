package pl.coderslab.gov_app.interpellation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gov_app.councilman.Councilman;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

    public List<Interpellation>getIntAnswer(Boolean answer){
        return interpellationRepository.intIsAnswer(answer);
    }

    public List<Interpellation> getInterpellationByCouncilId(Long id){
      return interpellationRepository.findByCouncilman_Id(id);
    }

    public boolean checkDate (Interpellation interpellation){
        Date interpellationDate = interpellation.getDate();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate interpellationLocalDate = LocalDate.parse(formatDate.format(interpellationDate));
        LocalDate answearDate = interpellationLocalDate.plusDays(30);
        LocalDate today = LocalDate.now();
        Long init = today.until(answearDate, ChronoUnit.DAYS);

        if(init<10){
            return false;
        }

        return true;
    }

}
