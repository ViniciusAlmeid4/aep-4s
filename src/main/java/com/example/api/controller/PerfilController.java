package com.example.api.controller;

import com.example.api.model.Perfil;
import com.example.api.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @GetMapping
    public List<Perfil> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Perfil buscar(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
    }

    @PostMapping
    public Perfil criar(@RequestBody Perfil perfil) {
        return repository.save(perfil);
    }

    @PutMapping("/{id}")
    public Perfil atualizar(@PathVariable Long id, @RequestBody Perfil dados) {
        Perfil perfil = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        perfil.setNome(dados.getNome());
        perfil.setEmail(dados.getEmail());
        return repository.save(perfil);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
