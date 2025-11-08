package com.example.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private LocalDateTime enviadaEm = LocalDateTime.now();

    // Uma mensagem pertence a um perfil (autor)
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil autor;

    // Uma mensagem pertence a um grupo
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
}