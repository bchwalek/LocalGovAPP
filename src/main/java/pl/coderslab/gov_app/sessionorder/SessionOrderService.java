package pl.coderslab.gov_app.sessionorder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.gov_app.superadmin.SuperAdmin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class SessionOrderService {

    SessionOrderRepository sessionOrderRepository;

    public List<SessionOrder> getAllSessionOrder(){
        return sessionOrderRepository.findAll();
    }

    public Optional<SessionOrder> getSessionOrder(Long id){
        return sessionOrderRepository.findById(id);
    }

    public void addSessionOrder(SessionOrder sessionOrder){
        sessionOrderRepository.save(sessionOrder);
    }

    public void deleteSessionOrder (Long id){
        sessionOrderRepository.deleteById(id);
    }

    public void updateCouncilman(SuperAdmin superAdmin) {

    }
}
