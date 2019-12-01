package com.db_test.mysqldatabaseaccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
	
	List<Employees> findByJobId (Integer jobId);
	Optional<Employees> findByIdAndJobId(Integer id, Integer jobId);

}
