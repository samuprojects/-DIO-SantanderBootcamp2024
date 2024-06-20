package me.dio.repository;


import me.dio.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByDataNascimentoBetween(LocalDate start, LocalDate end);
    boolean existsByNome(String nome);
}
