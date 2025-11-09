package com.example.api.controller;

import com.example.api.dto.ProjetoCreateDTO;
import com.example.api.dto.ProjetoDTO;
import com.example.api.mapper.EntityMapper;
import com.example.api.model.Perfil;
import com.example.api.model.Projeto;
import com.example.api.repository.PerfilRepository;
import com.example.api.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjetoDTO> listar() {
        return repository.findAll().stream()
                .map(EntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjetoDTO buscar(@PathVariable Long id) {
        Projeto projeto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        return EntityMapper.toDTO(projeto);
    }

    // Criar um projeto vinculado a um Perfil
    @PostMapping(value = "/perfil/{perfilId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProjetoDTO criar(@PathVariable Long perfilId, @RequestBody ProjetoCreateDTO dto) {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.titulo());
        projeto.setDescricao(dto.descricao());
        projeto.setTipo(dto.tipo());
        projeto.setAutor(perfil);
        Projeto saved = repository.save(projeto);
        return EntityMapper.toDTO(saved);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProjetoDTO atualizar(@PathVariable Long id, @RequestBody ProjetoCreateDTO dto) {
        Projeto projeto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        projeto.setTitulo(dto.titulo());
        projeto.setDescricao(dto.descricao());
        projeto.setTipo(dto.tipo());
        Projeto saved = repository.save(projeto);
        return EntityMapper.toDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
