package com.db_test.mysqldatabaseaccess;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {
	
	@Autowired
	EmployeesRepository employeesRepository;
	
	@Autowired
	JobsRepository jobsRepository;
	
	@GetMapping(value = "/rest/jobs/{jobId}/employees/all")
	public List<Employees> getAllEmployeesByJobId(@PathVariable Integer jobId) {
		if(!jobsRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("JobId " + jobId + " not found");
        }
		else {
			return employeesRepository.findByJobId(jobId);
		}
	}
	
	@PostMapping(value = "/rest/jobs/{jobId}/employees/add")
	public Employees addEmployee (@PathVariable Integer jobId, @RequestBody Employees employee) {
		return jobsRepository.findById(jobId)
			.map(job -> {
				employee.setJob(job);
				return employeesRepository.save(employee);
			})
			.orElseThrow(() -> new ResourceNotFoundException("JobId " + jobId + " not found"));
	}
	
	@GetMapping(value = "/rest/jobs/{jobId}/employees/{id}")
	public Optional<Employees> getEmployeeByIdAndJobId(@PathVariable Integer jobId, @PathVariable Integer id) {
		if(!jobsRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("JobId " + jobId + " not found");
        }
		else {
			return employeesRepository.findByIdAndJobId(id, jobId);
		}
	}
	
	@PutMapping(value = "/rest/jobs/{jobId}/employees/{id}")
	public Employees replaceEmployee(@PathVariable Integer jobId, @PathVariable Integer id, @RequestBody Employees newEmployee) {
		if(!jobsRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("JobId " + jobId + " not found");
        }
		return employeesRepository.findById(id)
				.map(employee -> {
					employee.setFirstName(newEmployee.getFirstName());
					employee.setLastName(newEmployee.getLastName());
					employee.setEmail(newEmployee.getEmail());
					return employeesRepository.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return employeesRepository.save(newEmployee);
				});
		
	}
	
	@DeleteMapping(value = "/rest/jobs/{jobId}/employees/{id}")
	public String deleteEmployee(@PathVariable Integer jobId, @PathVariable Integer id) {
		if(!jobsRepository.existsById(jobId)) {
			return "JobId " + jobId + " not found";
		}
		else {
			employeesRepository.deleteById(id);
			return "Employee removed";
		}
	}
	

}

































