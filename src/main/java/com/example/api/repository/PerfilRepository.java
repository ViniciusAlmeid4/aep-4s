package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // You can add custom queries later, e.g.:
    // Optional<Perfil> findByEmail(String email);
}
