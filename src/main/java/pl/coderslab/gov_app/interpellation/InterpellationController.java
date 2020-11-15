package pl.coderslab.gov_app.interpellation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class InterpellationController {

        InterpellationService interpellationService;

        @GetMapping("/showinterpellation")
        public String showAllInt(Model model){
          model.addAttribute("allint", interpellationService.getAllInterpellation());
          return "Interpellation-show-all";
        }


}
