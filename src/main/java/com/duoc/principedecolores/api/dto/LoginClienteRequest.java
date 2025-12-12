package com.duoc.principedecolores.api.dto;

import lombok.Data;

@Data
public class LoginClienteRequest {
    private String email;
    private String password;
}