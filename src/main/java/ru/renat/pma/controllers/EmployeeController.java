package ru.renat.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.renat.pma.entities.Employee;
import ru.renat.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	

	@GetMapping()
	public String displayEmployees(Model model) {
		List<Employee> employees = empService.getAll();
				
		model.addAttribute("employees", employees);
		return "employees/employeesList";
		
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
		
	}
	
	@PostMapping("/save")
	public String createEmployee(Model model, @Valid Employee employee, Errors errors) {
		
		if(errors.hasErrors())
			return "employees/new-employee";
					
		empService.save(employee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam ("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam ("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
	}
}
