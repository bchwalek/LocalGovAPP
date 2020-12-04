package pl.coderslab.gov_app;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.gov_app.councilman.Councilman;
import pl.coderslab.gov_app.councilman.CouncilmanService;
import pl.coderslab.gov_app.interpellation.Interpellation;
import pl.coderslab.gov_app.interpellation.InterpellationRepository;
import pl.coderslab.gov_app.interpellation.InterpellationService;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class HomeController {

    private CouncilmanService councilmanService;
    private InterpellationService interpellationService;
    private InterpellationRepository interpellationRepository;



    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

//    @PostMapping("/login")
//    public String login(){
//        return "index";
//    }

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
    public String start2() {

        Interpellation interpellation = interpellationService.getInerpellation(1L).get();

        if(checkDate(interpellation)){
            System.out.println("no dawaj");
        return "index";}

        return "redirect:/addinterpellation";
    }



//    @GetMapping("/showint")
//    public String showAllInt(ModelMap model){
//
//        Map<Interpellation, Boolean> intChceck = new HashMap<>();
//
//        for (Interpellation interpellation : interpellationService.getIntAnswer(false)) {
//            if(checkDate(interpellation)){
//                intChceck.put(interpellation, true);
//            } else intChceck.put(interpellation, false);
//        }
//
//        model.addAttribute("allint", intChceck);
//        return "Interpellation-show-all2";
//    }

//    @GetMapping("/intans/{id}")
//    public String addAnswerForm(@PathVariable("id") Long id, Model model){
//        model.addAttribute("interpellation", interpellationService.getInerpellation(id).get());
//
//        return "Interpellation-form-answer";
//    }
//
//    @PostMapping("/intans")
//    public String addAnswer(@ModelAttribute("id")String id,
//                            @ModelAttribute("answer")String answer, BindingResult bindingResult){
//      Interpellation interpellation =  interpellationService.getInerpellation(Long.parseLong(id)).get();
//      interpellation.setAnswer(answer);
//      interpellation.setAnswer(true);
//      interpellationService.addInterpellation(interpellation);
//
//        return "redirect:/showint";
//    }


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