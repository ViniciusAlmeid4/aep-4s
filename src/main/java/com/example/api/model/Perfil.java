package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String setor;
    @Column(columnDefinition = "TEXT")
    private String oferece; // O que a indústria oferece
    @Column(columnDefinition = "TEXT")
    private String busca; // O que busca em parceiros

    // Um perfil pode criar vários projetos
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Projeto> projetos;

    // Um perfil pode enviar várias mensagens
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mensagem> mensagens;
}