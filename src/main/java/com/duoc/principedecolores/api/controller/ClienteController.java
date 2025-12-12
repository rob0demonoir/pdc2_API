package com.duoc.principedecolores.api.controller;

import com.duoc.principedecolores.api.dto.LoginClienteRequest;
import com.duoc.principedecolores.api.dto.LoginClienteResponse;
import com.duoc.principedecolores.api.model.Cliente;
import com.duoc.principedecolores.api.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCliente(@RequestBody LoginClienteRequest request) {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(request.getEmail());

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();

            if (cliente.getPassword().equals(request.getPassword())) {

                return ResponseEntity.ok(new LoginClienteResponse("Login exitoso", null, cliente));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
    }

    public static class RegistroRequest {
        public String nombre;
        public String email;
        public String password;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarCliente(@RequestBody RegistroRequest request) {
        if (clienteRepository.existsByEmail(request.email)) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre);
        cliente.setEmail(request.email);
        cliente.setPassword(request.password);

        clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente registrado exitosamente");
    }
}