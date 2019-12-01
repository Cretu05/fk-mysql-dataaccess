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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rest/jobs")
public class JobsController {
	
	@Autowired
	JobsRepository jobsRepository;
	
	@GetMapping(value = "/all")
	public List<Jobs> getAllJobs (){
		return jobsRepository.findAll();
	}
	
	@PostMapping(value = "/add")
	public String addJob(@RequestBody Jobs job) {
		jobsRepository.save(job);
		return "Job saved with id = " + job.getId();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Jobs> getJobById (@PathVariable Integer id){
		return jobsRepository.findById(id);
	}
	
	@PutMapping(value = "/{id}")
	public Jobs replaceJob (@RequestBody Jobs newJob, @PathVariable Integer id) {
		return jobsRepository.findById(id)
				.map(job -> {
					job.setName(newJob.getName());
					return jobsRepository.save(job);
				})
				.orElseGet(() -> {
					newJob.setId(id);
					return jobsRepository.save(newJob);
				});
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteJob (@PathVariable Integer id) {
		jobsRepository.deleteById(id);
		return "Job " + id + " removed"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
