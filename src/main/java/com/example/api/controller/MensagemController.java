package com.example.api.controller;

import com.example.api.model.Grupo;
import com.example.api.model.Mensagem;
import com.example.api.model.Perfil;
import com.example.api.repository.GrupoRepository;
import com.example.api.repository.MensagemRepository;
import com.example.api.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public List<Mensagem> listar() {
        return repository.findAll();
    }

    // List messages by group
    @GetMapping("/grupo/{grupoId}")
    public List<Mensagem> listarPorGrupo(@PathVariable Long grupoId) {
        return repository.findByGrupoId(grupoId);
    }

    // List messages by profile
    @GetMapping("/perfil/{perfilId}")
    public List<Mensagem> listarPorPerfil(@PathVariable Long perfilId) {
        return repository.findByAutorId(perfilId);
    }

    // Create a message in a specific group by a specific profile
    @PostMapping("/grupo/{grupoId}/perfil/{perfilId}")
    public Mensagem criar(@PathVariable Long grupoId, @PathVariable Long perfilId, @RequestBody Mensagem mensagem) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        mensagem.setGrupo(grupo);
        mensagem.setAutor(perfil);
        return repository.save(mensagem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
