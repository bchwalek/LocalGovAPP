package pl.coderslab.gov_app.sessionelem;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Secured("ROLE_ADMIN")
public class SessionelemController {
    SessionelemService sessionelemService;

    @GetMapping("/addelem")
    public String addElemForm(Model model){
        model.addAttribute("elem", new Sessionelem());
        return "Elem-form-add";
    }

    @PostMapping("/addelem")
    public String addElem (Sessionelem sessionelem, BindingResult bindingResult){
        sessionelemService.addElem(sessionelem);
        return "redirect:/sessionadd2";
    }
}
