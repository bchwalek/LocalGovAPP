package pl.coderslab.gov_app.councilman;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.gov_app.interpellation.Interpellation;
import pl.coderslab.gov_app.interpellation.InterpellationService;
import pl.coderslab.gov_app.role.RoleService;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class CouncilmanController {

    CouncilmanService councilmanService;
    RoleService roleService;
    InterpellationService interpellationService;
    PasswordEncoder passwordEncoder;

    @Secured("ROLE_ADMIN")
    @GetMapping("/addcouncilman")
    public String addcouncilam(Model model) {
        model.addAttribute("councilman", new Councilman());
        model.addAttribute("roles", roleService.getAllRole());
        return "Councilman-form-add";
    }

    @PostMapping("/addcouncilman")
    public String addcoucilman(@Valid Councilman councilman, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Councilman-form-add";
        }
        councilman.setPassword(passwordEncoder.encode(councilman.getPassword()));
        councilman.setIsDelete(false);
        councilmanService.addCoucilman(councilman);
        return "redirect:/showallcouncilman";
    }

    @GetMapping("/showallcouncilman")
    public String showallcouncilman(Model model) {
        model.addAttribute("councilmans", councilmanService.getCouncilmanIsDelete(false));
        return "Councilman-show-all";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/showallcouncilman")
    public String showallCouncilmanAdmin(Model model) {
        model.addAttribute("councilmans", councilmanService.getAllCouncilman());
        model.addAttribute("warning", "Kliknięcie USUŃ - trwale usuwa użytkownia wraz z powiązanymi danymi");
        return "Councilman-show-admin-all";
    }

    @GetMapping("/showcouncilman/{id}")
    public String showcouncilman(@PathVariable Long id, Model model) {
        Councilman councilman = councilmanService.getCouncilman(id).get();
        model.addAttribute("councilman", councilman);
        model.addAttribute("CouncInterp", interpellationService.getInterpellationByCouncilId(id));
        return "Councilman-show-profile";
    }

    //referencja do klucza interpelacje
    @Secured("ROLE_ADMIN")
    @GetMapping("/deactivate/{id}")
    public String deactivate(@PathVariable Long id) {
        Councilman councilman = councilmanService.getCouncilman(id).get();
        councilman.setIsDelete(true);
        councilmanService.addCoucilman(councilman);

        return "redirect:/admin/showallcouncilman";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/activate/{id}")
    public String activate(@PathVariable Long id) {
        Councilman councilman = councilmanService.getCouncilman(id).get();
        councilman.setIsDelete(false);
        councilmanService.addCoucilman(councilman);

        return "redirect:/admin/showallcouncilman";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {


        List<Interpellation> interpellations = interpellationService.getAllInterpellation();
        Councilman councilman =councilmanService.getCouncilman(id).get();

        List<Interpellation>  interpellations2 = interpellations.stream().filter(interpellation -> interpellation.getCouncilman()==councilman).collect(Collectors.toList());

         for (Interpellation interpellation: interpellations2){
           interpellationService.deleteInterpellation(interpellation.getId());
         }
         int count =interpellations2.size();
        councilmanService.deleteCouncilman(id);
        return "redirect:/admin/showallcouncilman";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/updatecouncilman/{id}")
    public String updateCouncilmanForm(@PathVariable Long id, Model model) {
        model.addAttribute("councilman", councilmanService.getCouncilman(id).get());
        return "Councilman-form-update";
    }

    @PostMapping("/updatecouncilman")
    public String udpateCouncilan(@ModelAttribute("id") Long id,
                                  @ModelAttribute("email") String email,
                                  @ModelAttribute("firstName") String firstName,
                                  @ModelAttribute("lastName") String lastName,
                                  @ModelAttribute("description") String description,
                                  @ModelAttribute("role") Long role) {
        Councilman councilman = councilmanService.getCouncilman(id).get();
        councilman.setEmail(email);
        councilman.setFirstName(firstName);
        councilman.setLastName(lastName);
        councilman.setDescription(description);
        councilman.setRole(roleService.getRole(role).get());
        councilmanService.addCoucilman(councilman);
        return "redirect:/showallcouncilman";
    }

    @GetMapping("/")
    public String start(Model model){
        Random random = new Random();
        List<Councilman> counslimans = councilmanService.getAllCouncilman();
        Integer randomId = random.nextInt(counslimans.size()+1);
        model.addAttribute("councilman", councilmanService.getCouncilman(Long.valueOf(randomId)));
        return "index";
    }
}
