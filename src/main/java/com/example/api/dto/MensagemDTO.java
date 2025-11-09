package com.example.api.dto;

import java.time.LocalDateTime;

public record MensagemDTO(
    Long id,
    String conteudo,
    LocalDateTime enviadaEm,
    Long autorId,
    String autorNome,
    Long grupoId
) {}

