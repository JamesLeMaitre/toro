package com.torocommunication.torofull;

import com.torocommunication.torofull.entities.JobType;
import com.torocommunication.torofull.entities.RoleUEA;
import com.torocommunication.torofull.repo.RoleUEARepo;
import com.torocommunication.torofull.repo.TypeJobRepo;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
public class ToroFullApplication {
	private final RoleUEARepo roleUEARepo;

	private final TypeJobRepo typeJobRepo;

	public ToroFullApplication(RoleUEARepo roleUEARepo, TypeJobRepo typeJobRepo) {
		this.roleUEARepo = roleUEARepo;
		this.typeJobRepo = typeJobRepo;
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
			if (typeJobRepo.findAll().isEmpty()) {
				JobType jobType = new JobType();
				jobType.setLibelle("À plein temps");
				typeJobRepo.save(jobType);

				JobType jobType1 = new JobType();
				jobType1.setLibelle("À temps partiel");
				typeJobRepo.save(jobType1);


				JobType jobType2 = new JobType();
				jobType2.setLibelle("Saisonnier");
				typeJobRepo.save(jobType2);

				JobType jobType3 = new JobType();
				jobType3.setLibelle("Parcours menant à la permanence");
				typeJobRepo.save(jobType3);

			}


		};
	}
}
