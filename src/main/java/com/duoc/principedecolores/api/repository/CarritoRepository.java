package com.duoc.principedecolores.api.repository;

import com.duoc.principedecolores.api.model.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<ItemCarrito, Integer> {


    List<ItemCarrito> findByCliente_Id(Integer clienteId);


    Optional<ItemCarrito> findByCliente_IdAndProducto_Id(Integer clienteId, Integer productoId);


    void deleteByCliente_Id(Integer clienteId);
}