package me.dio.service;

import me.dio.domain.Aluno;
import me.dio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Aluno save(Aluno aluno) {
        if (repository.count() >= 15) {
            throw new RuntimeException("Máximo de 15 alunos atingido");
        }

        if (existsByNome(aluno.getNome())) {
            throw new RuntimeException("Usuário já existe.");
        }

        // Verifica se a data de nascimento é posterior à data atual
        LocalDate hoje = LocalDate.now();
        if (aluno.getDataNascimento().isAfter(hoje)) {
            throw new RuntimeException("A data de nascimento não pode ser posterior à data atual.");
        }

        // Verifica se a idade é inferior a 120 anos
        long idade = ChronoUnit.YEARS.between(aluno.getDataNascimento(), hoje);
        if (idade >= 120) {
            throw new RuntimeException("A data de nascimento não é válida. A idade deve ser inferior a 120 anos.");
        }

        return repository.save(aluno);
    }

    public Aluno update(Long id, Aluno aluno) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }
        aluno.setId(id);
        return repository.save(aluno);
    }

    public Page<Aluno> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Aluno> findAniversariantes() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return repository.findByDataNascimentoBetween(startOfWeek, endOfWeek);
    }

    public boolean existsByNome(String nome) {
        return repository.existsByNome(nome);
    }

}
