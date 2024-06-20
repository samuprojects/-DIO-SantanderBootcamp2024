package me.dio;

import me.dio.domain.Aluno;
import me.dio.repository.AlunoRepository;
import me.dio.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

	@InjectMocks
	private AlunoService service;

	@Mock
	private AlunoRepository repository;

	public AlunoServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void save() {
		Aluno aluno = new Aluno();
		aluno.setNome("Test");
		aluno.setDataNascimento(LocalDate.now());

		when(repository.save(any(Aluno.class))).thenReturn(aluno);
		when(repository.count()).thenReturn(10L);

		Aluno result = service.save(aluno);
		assertNotNull(result);
		assertEquals(aluno.getNome(), result.getNome());
	}

	@Test
	void findAll() {
		// Configuração do cenário
		Aluno aluno = new Aluno();
		aluno.setNome("Test");
		aluno.setDataNascimento(LocalDate.now());

		// Mock do comportamento do repository
		when(repository.findAll()).thenReturn(Collections.singletonList(aluno));

		// Chamada do método que está sendo testado
		List<Aluno> result = (List<Aluno>) service.findAll(0,10);

		// Verificação do resultado
		assertFalse(result.isEmpty());
		assertEquals(aluno.getNome(), result.get(0).getNome());
		// Outras verificações podem ser adicionadas conforme necessário
	}


	@Test
	void delete() {
		doNothing().when(repository).deleteById(anyLong());
		service.delete(1L);
		verify(repository, times(1)).deleteById(1L);
	}
}
