package ru.renat.pma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.renat.pma.dao.EmployeeRepository;
import ru.renat.pma.dto.EmployeeProject;
import ru.renat.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo; 
		
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public List<Employee> getAll(){
		return (List<Employee>) empRepo.findAll();
	}
	
	public Page<Employee> getAll(Pageable page){
		return empRepo.findAll(page);
	}
	
	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}
	
	public Optional<Employee> findById(Long id) {
		return empRepo.findById(id);
	}
	
	public void deleteById(Long id) {
		empRepo.deleteById(id);
	}
	
	public Employee findByEmail(String email) {
		return empRepo.findByEmail(email);
	}
	
	public Employee findByEmployeeId(Long id) {
		return empRepo.findByEmployeeId(id);
	}

	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
	}

}
