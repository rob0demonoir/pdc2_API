package com.duoc.principedecolores.api.dto;

import com.duoc.principedecolores.api.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginClienteResponse {
    private String mensaje;
    private String token; // Puede ser null por ahora si no usas JWT
    private Cliente cliente;
}