package com.benhirt.controllers;

import com.benhirt.entities.SousJacents;
import com.benhirt.exceptions.ResourceNotFoundException;
import com.benhirt.services.SousJacentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/sousJacent")
public class SousJacentsController {

    @Autowired
    private SousJacentsService sousJacentsService;

    @GetMapping(value = {"/","/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
        Page<SousJacents> pages = sousJacentsService.getAllSousJacents(id, 1, "id");
        model.addAttribute("pageable", pages);
        return "sousJacents/home";
    }


    @GetMapping("/add")
    public String add(ModelMap model, SousJacents sousJacents) {
        model.addAttribute("sousJacent", sousJacents);
        return "sousJacents/add";
    }

    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("sousJacent", sousJacentsService.findById(id));
        return "sousJacents/add";
    }

    @PostMapping("/save")
    public String saveSousJacent(@Valid @ModelAttribute("sousJacent") SousJacents sousJacents, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("sousJacent", sousJacents);
            return "sousJacents/add";
        }
        sousJacentsService.save(sousJacents);
        return "redirect:/sousJacent/";
    }

    @GetMapping("/delete/{page}/{id}")
    public String delete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        sousJacentsService.deleteById(id);
        return "redirect:/sousJacent/page/"+page;
    }
}
