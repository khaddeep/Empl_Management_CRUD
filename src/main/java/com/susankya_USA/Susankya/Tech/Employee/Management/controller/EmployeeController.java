package com.susankya_USA.Susankya.Tech.Employee.Management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.susankya_USA.Susankya.Tech.Employee.Management.exceptions.ResourceNotFoundExceptions;
import com.susankya_USA.Susankya.Tech.Employee.Management.model.Employee;
import com.susankya_USA.Susankya.Tech.Employee.Management.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	
	//Get all employee
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	//Create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id ) {
	Employee employee = employeeRepository.findById(id) 
			.orElseThrow(()-> new ResourceNotFoundExceptions("DNE with id: "+ id));
				//.orElseThrow(() -> new ResourceNotFoundExceptions("Employee DNE"));
		return ResponseEntity.ok(employee);
	}
	
	//update mapping
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeDetails, @PathVariable("id") Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("DNE"+id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable("id") Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("Empl DNE"+id));

		employeeRepository.delete(employee);
		 
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		
		map.put("Deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(map);	
	}

}
