package com.example.api.controller;

import com.example.api.dto.PerfilCreateDTO;
import com.example.api.dto.PerfilDTO;
import com.example.api.mapper.EntityMapper;
import com.example.api.model.Perfil;
import com.example.api.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PerfilDTO> listar() {
        return repository.findAll().stream()
                .map(EntityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PerfilDTO buscar(@PathVariable Long id) {
        Perfil perfil = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        return EntityMapper.toDTO(perfil);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PerfilDTO criar(@RequestBody PerfilCreateDTO dto) {
        Perfil perfil = new Perfil();
        perfil.setNome(dto.nome());
        perfil.setEmail(dto.email());
        perfil.setSetor(dto.setor());
        perfil.setOferece(dto.oferece());
        perfil.setBusca(dto.busca());
        Perfil saved = repository.save(perfil);
        return EntityMapper.toDTO(saved);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PerfilDTO atualizar(@PathVariable Long id, @RequestBody PerfilCreateDTO dto) {
        Perfil perfil = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        perfil.setNome(dto.nome());
        perfil.setEmail(dto.email());
        perfil.setSetor(dto.setor());
        perfil.setOferece(dto.oferece());
        perfil.setBusca(dto.busca());
        Perfil saved = repository.save(perfil);
        return EntityMapper.toDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
