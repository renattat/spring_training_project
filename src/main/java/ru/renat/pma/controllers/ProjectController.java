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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.renat.pma.dto.TimeChartData;
import ru.renat.pma.entities.Employee;
import ru.renat.pma.entities.Project;
import ru.renat.pma.services.EmployeeService;
import ru.renat.pma.services.ProjectService;


@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	
	@GetMapping()
	public String displayProjects(Model model) {
		
		List<Project> projects =  proService.getAll(); 
		
		model.addAttribute("projects", projects);
		
		return "projects/projectsList";
	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees =  empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Model model, @Valid Project project, Errors errors) {
		
		if (errors.hasErrors()) {
			return "projects/new-project";
		}
			
		
		proService.save(project);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String updateProject(@RequestParam("id") long theId, Model model) {
		
		Project theProject = proService.findById(theId).get();
		model.addAttribute("project", theProject);
		
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId) {
		
		Project theProject = proService.findById(theId).get();
		proService.delete(theProject);
		
		return "redirect:/projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimeLineString = objectMapper.writeValueAsString(timelineData);
		
		System.out.println("------------ project timelines -------------");
		System.out.println(jsonTimeLineString);
		
		model.addAttribute("projectTimeList", jsonTimeLineString);
		
		return "projects/project-timelines";
	}
}
