package com.benhirt.controllers;

import com.benhirt.converter.SousJacentsFormatter;
import com.benhirt.entities.Employee;
import com.benhirt.entities.SousJacents;
import com.benhirt.exceptions.ResourceNotFoundException;
import com.benhirt.services.DepartementService;
import com.benhirt.services.EmployeeService;
import com.benhirt.services.SousJacentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/employee"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SousJacentsService sousJacentsService;
    @Autowired
    private DepartementService departementService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "sousJacentsList",
                new SousJacentsFormatter(List.class));
    }

    @GetMapping(value = {"","/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
        Page<Employee> pages = employeeService.getAllEmployees(id, 3, "id");
        model.addAttribute("pageable", pages);

        return "employee/home";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable("id") long id,ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee/view";
    }


    @GetMapping("/add")
    public String add(ModelMap model, Employee employee) {
        model.addAttribute("sousJacents", sousJacentsService.getAllSousJacents());
        model.addAttribute("employee", employee);
        model.addAttribute("departement", departementService.getAllDepartements());
        return "employee/add";
    }

    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        Employee employee = employeeService.findByIdWithSousJacents(id);
        List<SousJacents> sousJacents = sousJacentsService.getAllSousJacents();
        sousJacents.forEach(e->{
            employee.getSousJacentsList().forEach(t->{
                if(e.getId() ==t.getId()){
                    e.setUsed(true);
                }
            });
        });

        model.addAttribute("sousJacents", sousJacents);
        model.addAttribute("departements", departementService.getAllDepartements());
        model.addAttribute("employee", employeeService.findByIdWithSousJacents(id));
        return "employee/add";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){

            model.addAttribute("sousJacents", sousJacentsService.getAllSousJacents());
            model.addAttribute("employee", employee);
            return "employee/add";
        }

        employeeService.save(employee);
        return "redirect:/employee/";
    }

    @GetMapping("/delete/{page}/{id}")
    public String delete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        employeeService.deleteById(id);
        return "redirect:/employee/page/"+page;
    }


    @GetMapping("/redirect")
    public String redirect(String st) {
        return "redirect:/"+st;
    }

}
