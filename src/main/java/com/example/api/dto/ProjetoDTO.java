package com.example.api.dto;

public record ProjetoDTO(
    Long id,
    String titulo,
    String descricao,
    String tipo,
    Long autorId,
    String autorNome
) {}

