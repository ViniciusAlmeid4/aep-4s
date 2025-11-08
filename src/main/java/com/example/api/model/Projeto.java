package com.example.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    // Ex: "Busco fornecedor X" ou "Ofereço capacidade Y"
    private String tipo; // opcional, pode representar a natureza do projeto

    // Um projeto pertence a um perfil
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil autor;
}