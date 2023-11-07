package com.github.marceloasfilho.extractdatabase.repository;

import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.model.Contrato;

import java.util.List;

public interface ContratoRepository {
    Contrato findContratoById(Integer contratoId);

    List<?> findAll();

    List<ContratoDTO> findAllContratosDetalhados();
}
