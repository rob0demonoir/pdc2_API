package com.duoc.principedecolores.api;

import com.duoc.principedecolores.api.model.Admin;
import com.duoc.principedecolores.api.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrincipedecoloresApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrincipedecoloresApiApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(AdminRepository adminRepository) {
        return args -> {

            if (adminRepository.findByUsername("admin").isEmpty()) {
                Admin adminUser = new Admin();
                adminUser.setUsername("admin");
                adminUser.setPassword("admin123"); // Contraseña en texto plano para este proyecto
                adminRepository.save(adminUser);
                System.out.println(">>> Usuario 'admin' creado exitosamente.");
            }
        };
    }
}
