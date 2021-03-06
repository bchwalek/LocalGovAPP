package pl.coderslab.gov_app.interpellation;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.Context;
import pl.coderslab.gov_app.LoginUserDetails;
import pl.coderslab.gov_app.PDFCreate;
import pl.coderslab.gov_app.councilman.CouncilmanService;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class InterpellationController {

    InterpellationService interpellationService;
    CouncilmanService councilmanService;
    PDFCreate pdfCreate;

    @GetMapping("/showinterpellation")
    public String showAllInt(Model model) {
        model.addAttribute("allint", interpellationService.getAllInterpellation());
        return "Interpellation-show-all";
    }


    @GetMapping("/interpellation/{id}")
    public String showIntById(@PathVariable Long id) throws IOException, DocumentException {

        Interpellation interpellation = interpellationService.getInerpellation(id).get();

        Context context = new Context();
        context.setVariable("title", interpellation.getTitle());
        context.setVariable("id", interpellation.id);
        context.setVariable("description", interpellation.getDescription());

        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        context.setVariable("date", formatDate.format(interpellation.getDate()));
        context.setVariable("councilmanFN", interpellation.getCouncilman().getFirstName());
        context.setVariable("councilmanLN", interpellation.getCouncilman().getLastName());

        PDFCreate pdfCreate = new PDFCreate();
        String html = pdfCreate.parseThymeleafTemplate("Interpellation-pdf-create", context);
        pdfCreate.generatePdfFromHtml(html, "Interpelacja nr " + interpellation.getId());
        return "redirect:/showinterpellation";
    }

    @Secured("ROLE_COUNCILMAN")
    @GetMapping("/addinterpellation")
    public String addIntForm(Model model) {
        model.addAttribute("interpellation", new Interpellation());
        return "Interpellation-form-add";
    }

    @Secured("ROLE_COUNCILMAN")
    @PostMapping("/addinterpellation")
    public String addInt(@Valid Interpellation interpellation,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal LoginUserDetails user) {

        if (bindingResult == null) {
            return "Interpellation-form-add";
        }


        interpellation.setCouncilman(councilmanService.getByEmail(user.getUsername()));
        interpellation.setDate(new Date());
        interpellation.setAnswer(false);
        interpellationService.addInterpellation(interpellation);

        return "redirect:/showinterpellation";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/showint")
    public String showAllInt(ModelMap model) {

        Map<Interpellation, Boolean> intChceck = new HashMap<>();
        List<Interpellation> intNoAnswer = interpellationService.getIntAnswer(false);

        if (intNoAnswer.isEmpty()) {
            model.addAttribute("intmessage", "Wszystkie Interpelacje mają dodaną odpowiedź!");
        }

        for (Interpellation interpellation : intNoAnswer) {
            if (interpellationService.checkDate(interpellation)) {
                intChceck.put(interpellation, true);
            } else intChceck.put(interpellation, false);
        }

        model.addAttribute("allint", intChceck);
        return "Interpellation-show-admin-noanswer";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/intans/{id}")
    public String addAnswerForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("interpellation", interpellationService.getInerpellation(id).get());

        return "Interpellation-form-answer";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/intans")
    public String addAnswer(@ModelAttribute("id") String id,
                            @ModelAttribute("answer") String answer, BindingResult bindingResult) {
        Interpellation interpellation = interpellationService.getInerpellation(Long.parseLong(id)).get();
        interpellation.setAnswer(answer);
        interpellation.setAnswer(true);
        interpellationService.addInterpellation(interpellation);

        return "redirect:/showint";
    }

    @Secured("ROLE_COUNCILMAN")
    @GetMapping("/deleteint/{id}")
    public String deleteInterpellation(@PathVariable("id") Long id){
        interpellationService.deleteInterpellation(id);
        return "redirect:/showinterpellation";
    }

}
