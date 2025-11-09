package com.example.api.dto;

public record PerfilDTO(
    Long id,
    String nome,
    String email,
    String setor,
    String oferece,
    String busca
) {}

