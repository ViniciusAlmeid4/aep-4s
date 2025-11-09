package com.example.api.controller;

import com.example.api.dto.GrupoDTO;
import com.example.api.mapper.EntityMapper;
import com.example.api.model.Grupo;
import com.example.api.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GrupoController {

    @Autowired
    private GrupoRepository repository;

    @GetMapping(path = {"/grupos", "/grupo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GrupoDTO> listar() {
        return repository.findAll().stream()
                .map(EntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = {"/grupos/{id}", "/grupo/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public GrupoDTO buscar(@PathVariable Long id) {
        Grupo grupo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        return EntityMapper.toDTO(grupo);
    }

    @PostMapping(path = {"/grupos", "/grupo"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GrupoDTO criar(@RequestBody GrupoDTO dto) {
        Grupo grupo = new Grupo();
        grupo.setNome(dto.nome());
        grupo.setDescricao(dto.descricao());
        Grupo saved = repository.save(grupo);
        return EntityMapper.toDTO(saved);
    }

    @PutMapping(path = {"/grupos/{id}", "/grupo/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GrupoDTO atualizar(@PathVariable Long id, @RequestBody GrupoDTO dto) {
        Grupo grupo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        grupo.setNome(dto.nome());
        grupo.setDescricao(dto.descricao());
        Grupo saved = repository.save(grupo);
        return EntityMapper.toDTO(saved);
    }

    @DeleteMapping(path = {"/grupos/{id}", "/grupo/{id}"})
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
