package com.benhirt.aspects;

import com.benhirt.controllers.DepartementController;
import com.benhirt.controllers.EmployeeController;
import com.benhirt.entities.Role;
import com.benhirt.services.DepartementService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ActionsManager {
    @Autowired
    private DepartementController departementController;
    private EmployeeController employeeController;
    private DepartementService departementService;


    @Around("execution(* com.benhirt.controllers.DepartementController.delete(..))")
    public String hello(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        System.err.println(args);
        if (!true) {
            Object r = jp.proceed(args);
            return r.toString();

        } else {
            return departementController.redirect(" ");
        }
    }

    @Around("execution(* com.benhirt.controllers.EmployeeController.add(..))")
    public String ajoutEmployee(ProceedingJoinPoint joinPoint) throws Throwable {
        if (departementController.getSession().getAttribute("roles") != null) {
            List<String> roless = (List<String>) departementController.getSession().getAttribute("roles");
            if (roless.contains("visitor")) {
                System.out.println("No privileges ");
                return departementController.redirect("");
            } else {
                joinPoint.proceed();
                return null;
            }
        }
        return departementController.redirect("");
    }

    @Around("execution(* com.benhirt.controllers.EmployeeController.delete(..))")
    public String delete (ProceedingJoinPoint jp) throws Throwable {
        Object [] args = jp.getArgs();
        if (departementController.getSession().getAttribute("roles") != null) {
            ArrayList<Role> roles = (ArrayList<Role>) departementController.getSession().getAttribute("roles");
            if (!roles.contains("admin") && !roles.contains("employee")) {
                System.err.println("no priveleges");

            }
            else if(roles.contains("employee") &&
                    departementService.isDepartementEmployee(Long.parseLong(departementController.getSession().
                            getAttribute("id").toString()) ,Long.parseLong(args[1].toString()) )){
                System.err.println("An employee ");
                System.err.println("Employee "+Long.parseLong(args[1].toString()));
                System.err.println("departement "+Long.parseLong(departementController.getSession().getAttribute("id").toString()));
                jp.proceed();
                return employeeController.redirect("employee");
            }
            else return employeeController.redirect("employee");
        }
        return departementController.redirect(" ");
    }

    @Around("execution(* com.benhirt.controllers.DepartementController.add(..))")
    public String ajoutDepartement(ProceedingJoinPoint jp) throws Throwable {
        if (departementController.getSession().getAttribute("roles")!= null){
            Object[] args = jp.getArgs();
            List<String> role = (List<String>) departementController.getSession().getAttribute("roles");
            for (String a : role) System.out.println(a + "---------------");
            if (role.contains("admin")) {
                jp.proceed();
                return null;
            }
            return departementController.redirect("");
        }
        return departementController.redirect("");

    }

   /* @Around("execution(* com.benhirt.controllers.DepartementController.edit(..))")
    public String editDepartement(ProceedingJoinPoint jp) throws Throwable
    {
        List<String> role = (List<String>)DepartementController.getSession().getAttribute("roles");
        for(String r : role) System.out.println("********"+r+"****");
        if (role.contains("admin")){
            jp.proceed();

            //System.out.println("You can't modify the info");

        }
        return (departementController.redirect(""));

    }
    @Around("execution(* com.benhirt.controllers.DepartementController.delete(..))")
    public String deleteDepartement(ProceedingJoinPoint jp) throws Throwable{
        List<String> role = (List<String>)departementController.getSession().getAttribute("roles");
        if (role.contains("admin")){
            jp.proceed();
            return (departementController.redirect(""));
        }
        return (departementController.redirect(""));
    }*/

    @Around("execution(* com.benhirt.controllers.DepartementController.add(..))")
    public String addDepartement(ProceedingJoinPoint jp) throws Throwable {
        if (departementController.getSession().getAttribute("roles")!= null){
            Object[] args = jp.getArgs();
            List<String> role = (List<String>) departementController.getSession().getAttribute("roles");
            if (role.contains("admin")) {
                jp.proceed();
                return null;
            }
            else return departementController.redirect("");
        }
        return departementController.redirect("");
    }
    @Around("execution(* com.benhirt.controllers.DepartementController.delete(..))")
    public String DeleteDepartement(ProceedingJoinPoint jp) throws Throwable {
        if (departementController.getSession().getAttribute("roles")!= null){
            Object[] args = jp.getArgs();
            List<String> role = (List<String>) departementController.getSession().getAttribute("roles");
            if (role.contains("admin")) {
                jp.proceed();
                return departementController.redirect("departement");
            }
            else return departementController.redirect("");
        }
        return departementController.redirect("");
        //return departementController.add(Departement(ModelMap(args[0]),Departement(args[1])));
    }
    @Around("execution(* com.benhirt.controllers.DepartementController.edit())")
    public String updateDepartement(ProceedingJoinPoint jp) throws Throwable {
        if (departementController.getSession().getAttribute("roles")!= null){
            Object[] args = jp.getArgs();
            List<String> role = (List<String>) departementController.getSession().getAttribute("roles");
            if (role.contains("admin")) {
                jp.proceed();
            }
            else return departementController.redirect("");
        }
        return departementController.redirect("");
    }

}
