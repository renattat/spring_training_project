package ru.renat.pma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.renat.pma.dao.ProjectRepository;
import ru.renat.pma.dto.ChartData;
import ru.renat.pma.dto.TimeChartData;
import ru.renat.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> getAll() {
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}
	
	public Optional<Project> findById(Long id) {
		return proRepo.findById(id);
	}
	
	public void deleteById(Long id) {
		proRepo.deleteById(id);
	}

	public void delete(Project theProject) {
		proRepo.delete(theProject);
	}
	
	public List<TimeChartData> getTimeData() {
		return proRepo.getTimeData();
	}
}
