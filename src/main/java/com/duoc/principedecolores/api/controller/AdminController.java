package com.duoc.principedecolores.api.controller;

import com.duoc.principedecolores.api.model.Admin;
import com.duoc.principedecolores.api.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AdminController {

    private final AdminRepository adminRepository;
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public static class LoginRequest{
        private String username;
        private String password;

        public String getUsername() {return username;}
        public void setUsername(String username) {this.username = username;}
        public String getPassword() {return password;}
        public void setPassword(String password) {this.password = password;}
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        Optional<Admin> adminOptional = adminRepository.findByUsername(loginRequest.getUsername());

        return adminOptional.map(admin -> {

            if (admin.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok("Sesión iniciada");
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        }
        ).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado"));
    }
}


