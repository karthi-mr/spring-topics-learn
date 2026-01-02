package com.learn.security;

import com.learn.security.auth.AuthenticationService;
import com.learn.security.auth.RegistrationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.learn.security.user.Role.ADMIN;
import static com.learn.security.user.Role.MANAGER;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			// admin
			var admin = RegistrationRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("admin")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).token());

			// manager
			var manager = RegistrationRequest.builder()
					.firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("admin")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).token());
		};
	}
}
