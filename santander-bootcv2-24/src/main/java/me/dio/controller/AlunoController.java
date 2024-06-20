package me.dio.controller;

import jakarta.validation.Valid;
import me.dio.domain.Aluno;
import me.dio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/alunos")
@Validated
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody Aluno aluno) {
        return ResponseEntity.ok(service.save(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        return ResponseEntity.ok(service.update(id, aluno));
    }

    @GetMapping
    public ResponseEntity<Page<Aluno>> getAlunos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aniversariantes")
    public ResponseEntity<List<Aluno>> getAniversariantes() {
        return ResponseEntity.ok(service.findAniversariantes());
    }

    @GetMapping("/exists")
    public ResponseEntity<Map<String, Boolean>> checkIfUserExists(@RequestParam String nome) {
        Map<String, Boolean> response = new HashMap<>();
        boolean exists = service.existsByNome(nome);
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

}
