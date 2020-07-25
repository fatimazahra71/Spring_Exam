package com.benhirt.controllers;

import com.benhirt.entities.Departement;
import com.benhirt.exceptions.ResourceNotFoundException;
import com.benhirt.services.DepartementService;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping({"/"})
@Getter
@Setter
public class DepartementController {
    @Autowired
    private DepartementService departementService;
    @Autowired
    private HttpSession session;

    @GetMapping(value = {"/departement","/departement/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model){
        Page<Departement> pages = departementService.getAllDepartements(id, 4, "id");
        model.addAttribute("pageable", pages);
        return "departement/home";
    }
    @GetMapping(value = {""})
    public String index(ModelMap model , Departement departement){
        model.addAttribute("departement", departement);
        return "departement/index";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("departement") Departement departement, BindingResult result, ModelMap model ) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            model.addAttribute("departement", departement);
            System.out.println(result);
            return "departement/index";
        }
        return "departement/index";
    }
    @GetMapping("/departement/add")
    public String add(ModelMap model, Departement departement) {
        model.addAttribute("departement", departement);
        return "departement/add";
    }

    @PostMapping("/departement/save")
    public String saveEmployee(@Valid @ModelAttribute("departement") Departement departement, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("departement", departement);
            System.out.println("has error");
            return "departement/add";
        }
        departementService.save(departement);
        return "redirect:/departement/";
    }
    @Pointcut("execution( String com.benhirt.controllers.DepartementController.view(id,model))")
    @RequestMapping("/departement/view/{id}")
    public String view(@PathVariable("id") long id,ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("departement", departementService.findById(id));
        model.addAttribute("employees" , departementService.getEmployeesOfDepartement(id));
        return "departement/view";
    }

    @GetMapping("/departement/delete/{page}/{id}")
    public String delete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        departementService.deleteById(id);
        return "redirect:/departement/page/"+page;
    }

    @GetMapping("/departement/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        Departement departement = departementService.findById(id);
        model.addAttribute("departement", departement);


        return "departement/add";
    }
    @GetMapping("/redirect")
    public String redirect(String st) {
        return "redirect:/"+st;
    }
}
