package pl.coderslab.gov_app.sessionorder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.gov_app.sessionelem.Sessionelem;
import pl.coderslab.gov_app.sessionelem.SessionelemService;

import java.util.List;


@AllArgsConstructor
@Controller
public class SessionOrderController {

    SessionOrderService sessionOrderService;
    SessionelemService sessionelemService;
    List<Sessionelem> sessionelemList;




    @GetMapping("/sessionadd")
    public String sessionaddform(Model model, @ModelAttribute("tempdata") SessionOrder sessionOrder, @ModelAttribute("tempSessionelem") Sessionelem sessionelem, BindingResult bindingResult){
        model.addAttribute("order", sessionOrder);
        model.addAttribute("elements", sessionelemService.getAllElem());
        sessionelemList.add(sessionelem);
        model.addAttribute("sessionElem", sessionelemList);
        return "Sessionorder-create";
    }

    @PostMapping("/sessionadd")
    public String sessionad(SessionOrder sessionOrder, Sessionelem sessionelem, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "Sessionorder-create";
        }
        redirectAttributes.addFlashAttribute("tempSessionelem", sessionelem);
        redirectAttributes.addFlashAttribute("tempdata", sessionOrder);
        return "redirect:/sessionadd";
    }


}

