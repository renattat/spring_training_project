package ru.renat.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.renat.pma.dao.EmployeeRepository;
import ru.renat.pma.dao.ProjectRepository;
import ru.renat.pma.dto.ChartData;
import ru.renat.pma.dto.EmployeeProject;
import ru.renat.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version:default value}")
	private String ver;

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
		// we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		// Lets convert projectData object into a json structure for use in javaSrtipt
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		//[{"NOTSTARTED", 1}, {"INPROGERESS", 2}, {"COMPLETED", 1}]
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		// we are querying the database for employees
		List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCnt", employeesProjectCount);
		
		return "main/home";
	}
}
