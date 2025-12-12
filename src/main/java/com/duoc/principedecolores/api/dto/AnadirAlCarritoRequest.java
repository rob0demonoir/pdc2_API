package com.duoc.principedecolores.api.dto;

import lombok.Data;

@Data
public class AnadirAlCarritoRequest {
    private Integer idProducto;
    private int cantidad;
    private Integer idCliente; // ID del cliente que está comprando
}