package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    // Example of a custom query:
    // List<Projeto> findByAutorId(Long perfilId);
}
