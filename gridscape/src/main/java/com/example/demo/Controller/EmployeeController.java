package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Employee;
import com.example.demo.servive.EmpService;

@Controller
public class EmployeeController {

	 @Autowired
	    private EmpService service;

	    @GetMapping("/")
	    public String home() {
	        return "index";
	    }

	    @GetMapping("/registrationts")
	    public String registration(Employee employee) {
	        return "registrationts";
	    }

	    @PostMapping("/add")
	    public String addEmployee(
	            @Validated @ModelAttribute("employee") Employee employee,
	            BindingResult result,
	            @RequestParam("photoFile") MultipartFile photoFile
	    ) throws IOException {
	        if (result.hasErrors()) {
	            return "registrationts";
	        }

	        service.saveEmployee(employee, photoFile);
	        return "redirect:/registrationts";
	    }

		
		  @GetMapping("/list")
		  public String listEmployees(Model model,Employee employee) {
		  List<Employee> employees = service.getEmployees();
		  model.addAttribute("employees",employees);
		  return "list"; }
		 
	
	
	
}
