package com.example.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Mensagem;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    // Example of useful custom queries:
    List<Mensagem> findByGrupoId(Long grupoId);
    List<Mensagem> findByAutorId(Long perfilId);
}

