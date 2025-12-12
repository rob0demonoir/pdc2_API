package com.duoc.principedecolores.api.dto;

import com.duoc.principedecolores.api.model.ItemCarrito;
import lombok.Data;
import java.util.List;

@Data
public class CarritoDto {
    private List<ItemCarrito> items;
    private int totalGeneral;
    private int cantidadProductos;

    public CarritoDto(List<ItemCarrito> items) {
        this.items = items;

        this.cantidadProductos = items.stream()
                .mapToInt(ItemCarrito::getCantidad)
                .sum();

        this.totalGeneral = items.stream()
                .mapToInt(item -> item.getCantidad() * item.getProducto().getPrice())
                .sum();
    }
}