package com.example.api.mapper;

import com.example.api.dto.*;
import com.example.api.model.Grupo;
import com.example.api.model.Mensagem;
import com.example.api.model.Perfil;
import com.example.api.model.Projeto;

public class EntityMapper {

    public static PerfilDTO toDTO(Perfil p) {
        if (p == null) return null;
        return new PerfilDTO(
            p.getId(),
            p.getNome(),
            p.getEmail(),
            p.getSetor(),
            p.getOferece(),
            p.getBusca()
        );
    }

    public static ProjetoDTO toDTO(Projeto prj) {
        if (prj == null) return null;
        return new ProjetoDTO(
            prj.getId(),
            prj.getTitulo(),
            prj.getDescricao(),
            prj.getTipo(),
            prj.getAutor() != null ? prj.getAutor().getId() : null,
            prj.getAutor() != null ? prj.getAutor().getNome() : null
        );
    }

    public static GrupoDTO toDTO(Grupo g) {
        if (g == null) return null;
        return new GrupoDTO(
            g.getId(),
            g.getNome(),
            g.getDescricao()
        );
    }

    public static MensagemDTO toDTO(Mensagem m) {
        if (m == null) return null;
        return new MensagemDTO(
            m.getId(),
            m.getConteudo(),
            m.getEnviadaEm(),
            m.getAutor() != null ? m.getAutor().getId() : null,
            m.getAutor() != null ? m.getAutor().getNome() : null,
            m.getGrupo() != null ? m.getGrupo().getId() : null
        );
    }
}

