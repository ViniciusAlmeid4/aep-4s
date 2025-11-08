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
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    // Um grupo contém várias mensagens
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Mensagem> mensagens;
}
