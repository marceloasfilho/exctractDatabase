package com.github.marceloasfilho.extractdatabase.repository;

import com.github.marceloasfilho.extractdatabase.model.Contrato;
import com.github.marceloasfilho.extractdatabase.repository.impl.ContratoRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ContratoRepositoryTest {
    @InjectMocks
    private ContratoRepositoryImpl contratoRepository;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void deveEncontrarUmContratoPorId() {
        // Cenário
        Object[] rows = {1L, "Licitação"};

        // Mock
        when(this.entityManager.createNativeQuery(anyString())).thenReturn(this.query);
        when(this.query.getSingleResult()).thenReturn(rows);

        // Ação
        Contrato contratoById = this.contratoRepository.findContratoById(1);

        // Verificação
        assertNotNull(contratoById);
        assertEquals(1, contratoById.getContratoId());
        assertEquals("Licitação", contratoById.getNome());
    }

    @Test
    public void deveEncontrarTodosContratos() {
        // Cenário
        List<Object[]> rows = new ArrayList<>();
        rows.add(new Object[]{1L, "Licitação"});
        rows.add(new Object[]{2L, "Mudança"});
        rows.add(new Object[]{3L, "Construção"});

        // Mock
        when(this.entityManager.createNativeQuery(anyString())).thenReturn(this.query);
        when(this.query.getResultList()).thenReturn(rows);

        // Ação
        List<?> todosContratos = this.contratoRepository.findAll();

        // Verificação
        assertNotNull(todosContratos);
        assertNotEquals(0, todosContratos.size());
    }
}
