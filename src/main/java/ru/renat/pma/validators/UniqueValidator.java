package ru.renat.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ru.renat.pma.entities.Employee;
import ru.renat.pma.services.EmployeeService;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

	@Autowired
	EmployeeService empService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered validation method..");
		Employee emp = empService.findByEmail(value);
		
		if(emp != null)
			return false;
		else
			return true;
	}

}
