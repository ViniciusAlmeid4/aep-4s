package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    // You can add queries like:
    // Optional<Grupo> findByNome(String nome);
}

