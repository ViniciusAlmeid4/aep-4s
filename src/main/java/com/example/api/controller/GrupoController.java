package com.example.api.controller;

import com.example.api.model.Grupo;
import com.example.api.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository repository;

    @GetMapping
    public List<Grupo> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Grupo buscar(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

    @PostMapping
    public Grupo criar(@RequestBody Grupo grupo) {
        return repository.save(grupo);
    }

    @PutMapping("/{id}")
    public Grupo atualizar(@PathVariable Long id, @RequestBody Grupo dados) {
        Grupo grupo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        grupo.setNome(dados.getNome());
        grupo.setDescricao(dados.getDescricao());
        return repository.save(grupo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
