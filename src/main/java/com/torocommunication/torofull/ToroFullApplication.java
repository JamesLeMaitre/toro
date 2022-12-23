package com.torocommunication.torofull;

import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.repo.RoleUEARepo;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableCaching
public class ToroFullApplication {
	private final RoleUEARepo roleUEARepo;

	public ToroFullApplication(RoleUEARepo roleUEARepo) {
		this.roleUEARepo = roleUEARepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ToroFullApplication.class, args);
	}




	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public CommandLineRunner runner(UtilisateurUEAInterface userService, RoleUEARepo roleUEARepo) {
		return args -> {
			if (roleUEARepo.findAll().isEmpty()) {
				RoleUEA role = new RoleUEA();
				role.setRolename("ROLE_ADMIN");
				roleUEARepo.save(role);

				RoleUEA role2 = new RoleUEA();
				role2.setRolename("ROLE_USER");
				roleUEARepo.save(role2);

			}


		};
	}
}
