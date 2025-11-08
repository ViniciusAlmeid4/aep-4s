package com.example.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    // Um perfil pode criar vários projetos
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

    // Um perfil pode enviar várias mensagens
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Mensagem> mensagens;
}