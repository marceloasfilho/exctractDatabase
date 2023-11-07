package com.github.marceloasfilho.extractdatabase.repository;

import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.model.Contrato;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class ContratoRepositoryDatabaseTest {
    @Autowired
    ContratoRepository contratoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve encontrar um Contrato por ID")
    public void deveEncontrarContratoPorId() {
        // Cenário
        Contrato contrato = new Contrato(1, "Licitação");
        // Ação
        Contrato contratoById = contratoRepository.findContratoById(1);

        // Verificação
        assertNotNull(contratoById);
        assertEquals(contrato.getContratoId(), contratoById.getContratoId());
        assertEquals(contrato.getNome(), contratoById.getNome());
    }

    @Test
    @DisplayName("Deve encontrar todos os contratos detalhados")
    public void deveEncontrarTodosOsContratosDetalhados() {
        // Ação
        List<ContratoDTO> todos = contratoRepository.findAllContratosDetalhados();

        // Verificação
        assertNotNull(todos);
        assertNotEquals(0, todos.size());
    }
}
