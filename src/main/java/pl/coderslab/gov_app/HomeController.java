package pl.coderslab.gov_app;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.gov_app.councilman.Councilman;
import pl.coderslab.gov_app.councilman.CouncilmanService;
import pl.coderslab.gov_app.interpellation.Interpellation;
import pl.coderslab.gov_app.interpellation.InterpellationRepository;
import pl.coderslab.gov_app.interpellation.InterpellationService;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private CouncilmanService councilmanService;
    private InterpellationService interpellationService;
    private InterpellationRepository interpellationRepository;


    @GetMapping("/radny")
    @ResponseBody
    public List<Interpellation> start(){
        Councilman councilman=new Councilman();
        councilman.setFirstName("A+");
        councilman.setLastName("B2");
        councilman.setCommittee("Budżet i Tyle");
        councilman.setDescription("Fajny Radny bo jest git");
        councilmanService.addCoucilman(councilman);


        Interpellation interpellation = new Interpellation();
        interpellation.setCouncilman(councilman);
        interpellation.setDescription("cos cos");
        interpellation.setTitle("11");
        interpellationService.addInterpellation(interpellation);

        Interpellation interpellation2 = new Interpellation();
        interpellation2.setCouncilman(councilman);
        interpellation2.setDescription("cos2 cos2");
        interpellation2.setTitle("22");
        interpellationService.addInterpellation(interpellation2);



        return interpellationRepository.findByCouncilman_Id(1L);

    }

    @GetMapping("/start")
    public String start2()  {

        return "Interpellation-form-add";
    }


}