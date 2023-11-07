package com.github.marceloasfilho.extractdatabase.service.impl;

import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.model.Contrato;
import com.github.marceloasfilho.extractdatabase.repository.ContratoRepository;
import com.github.marceloasfilho.extractdatabase.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {
    private final ContratoRepository contratoRepository;

    @Override
    public Contrato findContratoById(Integer contratoId) {
        return this.contratoRepository.findContratoById(contratoId);
    }

    @Override
    public List<?> findAll() {
        return this.contratoRepository.findAll();
    }

    @Override
    public List<ContratoDTO> findAllDetailedContratos() {
        return this.contratoRepository.findAllContratosDetalhados();
    }
}