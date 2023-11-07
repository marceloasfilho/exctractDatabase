package com.github.marceloasfilho.extractdatabase.service;

import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.model.Contrato;

import java.util.List;

public interface ContratoService {
    Contrato findContratoById(Integer uuid);

    List<?> findAll();

    List<ContratoDTO> findAllDetailedContratos();
}