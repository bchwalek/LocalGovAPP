package pl.coderslab.gov_app.sessionorder;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.gov_app.legal.LegalService;
import pl.coderslab.gov_app.resolution.Resolution;
import pl.coderslab.gov_app.resolution.ResolutionService;
import pl.coderslab.gov_app.sessionelem.Sessionelem;
import pl.coderslab.gov_app.sessionelem.SessionelemService;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;


@AllArgsConstructor
@Controller
public class SessionOrderController {

    SessionOrderService sessionOrderService;
    SessionelemService sessionelemService;
    List<Sessionelem> sessionelemList;
    ResolutionService resolutionService;
    LegalService legalService;



//Start formularza
    @Secured("ROLE_ADMIN")
    @GetMapping("/sessionadd")
        public String sessionAddStep1(Model model){
        model.addAttribute("order", new SessionOrder());
        return "Sessionorder-create-step-1";
    }

//Odbiór z formularza I etap Porządku (nr, data, godzina);
//Przejście do formularza z elementami porządku obrad, przesłanie elementów porządku obrad, i id rozpoczetej sesji
    @Secured("ROLE_ADMIN")
    @PostMapping("/sessionadd")
    public String sessionAddStep2(Model model, @Valid SessionOrder sessionOrder, @ModelAttribute("legal")String leagl, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "Sessionorder-create-step-1";
        }
       sessionOrder.setLegal(legalService.getLegal(Long.parseLong(leagl)).get());
       sessionOrderService.addSessionOrder(sessionOrder);

      long id = sessionOrder.getId();


       model.addAttribute("sessionElements", sessionelemService.getAllElem());
       model.addAttribute("sessionID", sessionOrder.getId());


       return "Sessionorder-create-step-2";
    }

    //Odbiór z forumlarza krok 2

    @Secured("ROLE_ADMIN")
    @PostMapping("/sessionadd2")
    public String sessionAddStep3(Model model,
                                  @ModelAttribute("sessionID") String sessionID,
                                  @ModelAttribute("elementID") String elementID,
                                  @RequestParam(required = false, value = "elementID") String idValue,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if(idValue!=null){
            Sessionelem sessionelem = sessionelemService.getElem(Long.parseLong(elementID)).get();
            sessionelemList.add(sessionelem);
            redirectAttributes.addFlashAttribute("elem", sessionelemList);
            redirectAttributes.addFlashAttribute("sessionID", sessionID);
            return "redirect:/sessionadd2";
        }

        SessionOrder sessionOrder = sessionOrderService.getSessionOrder(Long.parseLong(sessionID)).get();
        sessionOrder.setElems(sessionelemList);
        sessionOrderService.addSessionOrder(sessionOrder);
        redirectAttributes.addFlashAttribute("sessionID", sessionID);

        sessionelemList.clear();


        return "Sessionorder-create-step-3";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/sessionadd2")
    public String sessionAddStep3(Model model,
                                  @ModelAttribute("elem") List<Sessionelem> sessionelems,
                                  @ModelAttribute("sessionID") String sessionID) {

        model.addAttribute("sessionElements", sessionelemService.getAllElem());

        model.addAttribute("elem", sessionelems);

        return "Sessionorder-create-step-2";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/upload")
    public String upolad (@RequestParam("document")MultipartFile multipartFile,
                          @RequestParam("resolutionName")String resolutionName,
                          @RequestParam("sessionID")String sesionID,
                          RedirectAttributes redirectAttributes) throws IOException {


        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));


        Resolution resolution = new Resolution();
        resolution.setName(fileName);
        resolution.setResolutionName(resolutionName);
        resolution.setContent(multipartFile.getBytes());
        resolution.setSize(multipartFile.getSize());
        resolution.setUploadTime(new Date());
        resolution.setSessionOrder(sessionOrderService.getSessionOrder(Long.parseLong(sesionID)).get());

        resolutionService.addResolution(resolution);

        redirectAttributes.addFlashAttribute("message", "Plik doddany!");
        redirectAttributes.addFlashAttribute("sessionID", sesionID);

        return "redirect:/upload";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/upload")
    public  String upload(){
        return "Sessionorder-create-step-3";
    }

    @GetMapping("/display/{id}")
    public String displaySession(Model model, @PathVariable String id){
        model.addAttribute("sessionOrder", sessionOrderService.getSessionOrder(Long.parseLong(id)).get());
        model.addAttribute("element", sessionelemService.getSessionElemByOrderID(Long.parseLong(id)));

     List<Sessionelem>   elemntySesji = sessionelemService.getSessionElemByOrderID(Long.parseLong(id));

     boolean elemContains=false;

     for (Sessionelem elem:elemntySesji) {
          if(elemContains= elem.getName().equals("Podjęcie Uchwał")){
              model.addAttribute("messageRes", "Lista projektow uchwał:");
              model.addAttribute("resolutions", resolutionService.getResolutionByOrderId(Long.parseLong(id)));
          }
        }

        return "Sessionorder-display";
    }

    @GetMapping("/downlad/{id}")
    public void dwonFile(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
        Optional<Resolution> resault = resolutionService.getResolution(id);
        if(!resault.isPresent()){
            throw new Exception("Nie ma takiego pliku");
        }
        Resolution resolution = resault.get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Dispostion";
        String headerValue = "attachment, filename" + resolution.getResolutionName();
        response.setHeader(headerKey, headerValue);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(resolution.getContent());
        servletOutputStream.close();
    }

    @GetMapping("/allorders")
    public String showAll(Model model){
        List<SessionOrder> sessionOrders = sessionOrderService.getAllSessionOrder();
        Collections.reverse(sessionOrders);
        model.addAttribute("sessionsOrders", sessionOrders);
        return "Sessionorder-show-all";
    }

}

