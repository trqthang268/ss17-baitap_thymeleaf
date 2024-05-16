package ra.demo_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ra.demo_thymeleaf.dao.IEmployeeDao;
import ra.demo_thymeleaf.entity.Employee;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeDao employeeDao;

    @RequestMapping(value = {"/","/home"})
    public String home(Model model) {
        List<Employee> list = employeeDao.getEmployees();
        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping(value = "/initInsertEmployee")
    public String insertEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "insertEmployee";
    }

    @PostMapping(value = "/insertEmployee")
    public String insertEmployee(@Validated @ModelAttribute("employee") Employee e, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            employeeDao.addEmployee(e);
            return "redirect:/home";
        }else {
            model.addAttribute("employee",e);
            return "insertEmployee";
        }
    }

    @GetMapping("/updateEmployee/{id}")
    public String initUpdateEmployee(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@Validated @ModelAttribute("employee") Employee e, Model model,BindingResult result) {
        if (result.hasErrors()){
            model.addAttribute("employee",e);
            return "updateEmployee";
        }else {
        employeeDao.updateEmployee(e);
        return "redirect:/home";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.deleteEmployee(id);
        return "redirect:/home";
    }


}
