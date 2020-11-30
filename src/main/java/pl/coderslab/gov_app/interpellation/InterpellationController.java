package pl.coderslab.gov_app.interpellation;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.context.Context;
import pl.coderslab.gov_app.PDFCreate;
import pl.coderslab.gov_app.councilman.CouncilmanService;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Controller
public class InterpellationController {

        InterpellationService interpellationService;
        CouncilmanService councilmanService;
        PDFCreate pdfCreate;

        @GetMapping("/showinterpellation")
        public String showAllInt(Model model){
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
        context.setVariable("date", interpellation.getDate());
        context.setVariable("councilmanFN", interpellation.getCouncilman().getFirstName());
        context.setVariable("councilmanLN", interpellation.getCouncilman().getLastName());

        PDFCreate pdfCreate = new PDFCreate();
        String html = pdfCreate.parseThymeleafTemplate("Interpellation-pdf-create", context);
        pdfCreate.generatePdfFromHtml(html, "Interpelacja nr "+interpellation.getId());
        return "redirect:/showinterpellation";
    }

    @GetMapping("/addinterpellation")
    public String addIntForm(Model model){
            model.addAttribute("interpellation", new Interpellation());
            return "Interpellation-form-add";
    }


    @PostMapping("/addinterpellation")
    public String addInt(Interpellation interpellation, BindingResult bindingResult){
            if(bindingResult==null){
                return "Interpellation-form-add";
            }
            interpellation.setCouncilman(councilmanService.getCouncilman(1L).get());
            interpellation.setDate(new Date());
            interpellationService.addInterpellation(interpellation);




            return "redirect:/showinterpellation";
    }
}
