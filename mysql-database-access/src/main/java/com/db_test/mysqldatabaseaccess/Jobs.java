package com.db_test.mysqldatabaseaccess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Jobs {
	
	@Id
	@GeneratedValue
	@Column(name = "job_id")
	private Integer id;
	
	@Column(name = "job_name")
	private String name;
	
	public Jobs() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
