package com.db_test.mysqldatabaseaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.db_test.mysqldatabaseaccess")
@SpringBootApplication
public class MysqlDatabaseAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlDatabaseAccessApplication.class, args);
	}

}
