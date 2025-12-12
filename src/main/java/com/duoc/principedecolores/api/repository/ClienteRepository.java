package com.duoc.principedecolores.api.repository;

import com.duoc.principedecolores.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
}