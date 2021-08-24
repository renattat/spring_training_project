package ru.renat.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.renat.pma.entities.Project;
import ru.renat.pma.services.ProjectService;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectService proService;
	
	@GetMapping
	public Iterable<Project> getProjects(){
		
		return proService.getAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") long id) {
		
		return proService.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		
		return proService.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project update(@RequestBody @Valid Project project) {
		
		return proService.save(project);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Project partialUpdate(@PathVariable ("id") long id, @RequestBody @Valid Project patchProject){
		Project proj = proService.findById(id).get();
		
		if(patchProject.getName() != null) {
			proj.setName(patchProject.getName());
		}
		
		if(patchProject.getDescription() != null) {
			proj.setDescription(patchProject.getDescription());
		}
		
		if(patchProject.getStage() != null) {
			proj.setStage(patchProject.getStage());
		}
		
		return proService.save(proj);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable ("id") long id) {
		try {
			proService.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			
		}
	}
	
	

}
