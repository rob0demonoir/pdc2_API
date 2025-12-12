package com.duoc.principedecolores.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "items_carrito")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;


    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, updatable = false)
    private Long anadido;

    @PrePersist
    protected void onAnadido() {
        this.anadido = System.currentTimeMillis();
    }
}