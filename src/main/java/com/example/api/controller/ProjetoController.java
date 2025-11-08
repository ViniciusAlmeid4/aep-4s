package com.example.api.controller;

import com.example.api.model.Perfil;
import com.example.api.model.Projeto;
import com.example.api.repository.PerfilRepository;
import com.example.api.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Projeto> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Projeto buscar(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    // Criar um projeto vinculado a um Perfil
    @PostMapping("/perfil/{perfilId}")
    public Projeto criar(@PathVariable Long perfilId, @RequestBody Projeto projeto) {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        projeto.setAutor(perfil);
        return repository.save(projeto);
    }

    @PutMapping("/{id}")
    public Projeto atualizar(@PathVariable Long id, @RequestBody Projeto dados) {
        Projeto projeto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        projeto.setTitulo(dados.getTitulo());
        projeto.setDescricao(dados.getDescricao());
        projeto.setTipo(dados.getTipo());
        return repository.save(projeto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
