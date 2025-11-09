package com.example.api.dto;

public record PerfilCreateDTO(
    String nome,
    String email,
    String setor,
    String oferece,
    String busca
) {}

