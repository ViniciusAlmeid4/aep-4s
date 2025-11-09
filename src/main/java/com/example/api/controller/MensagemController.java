package com.example.api.controller;

import com.example.api.dto.MensagemCreateDTO;
import com.example.api.dto.MensagemDTO;
import com.example.api.mapper.EntityMapper;
import com.example.api.model.Grupo;
import com.example.api.model.Mensagem;
import com.example.api.model.Perfil;
import com.example.api.repository.GrupoRepository;
import com.example.api.repository.MensagemRepository;
import com.example.api.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private MensagemRepository repository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    // List all messages
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MensagemDTO> listar() {
        return repository.findAll().stream()
                .map(EntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    // List messages by group
    @GetMapping(path = {"/grupo/{grupoId}", "/mensagem/grupo/{grupoId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MensagemDTO> listarPorGrupo(@PathVariable Long grupoId) {
        return repository.findByGrupoId(grupoId).stream()
                .map(EntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    // List messages by profile
    @GetMapping(value = "/perfil/{perfilId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MensagemDTO> listarPorPerfil(@PathVariable Long perfilId) {
        return repository.findByAutorId(perfilId).stream()
                .map(EntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Create a message in a specific group by a specific profile
    @PostMapping(path = {"/grupo/{grupoId}/perfil/{perfilId}", "/mensagem/grupo/{grupoId}/perfil/{perfilId}"}, 
                 produces = MediaType.APPLICATION_JSON_VALUE, 
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    public MensagemDTO criar(@PathVariable Long grupoId, @PathVariable Long perfilId, @RequestBody MensagemCreateDTO dto) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(dto.conteudo());
        mensagem.setGrupo(grupo);
        mensagem.setAutor(perfil);
        Mensagem saved = repository.save(mensagem);
        return EntityMapper.toDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
