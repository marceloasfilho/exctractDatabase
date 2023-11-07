package com.github.marceloasfilho.extractdatabase.controller;

import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.model.Contrato;
import com.github.marceloasfilho.extractdatabase.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrato")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ContratoController {
    private final ContratoService contratoService;

    @GetMapping("/{contratoId}")
    public ResponseEntity<?> findContratoById(@PathVariable(value = "contratoId") Integer contratoId) {
        Contrato contrato = this.contratoService.findContratoById(contratoId);
        return contrato == null ? new ResponseEntity<>("Contrato não encontrado", HttpStatus.NOT_FOUND) : new ResponseEntity<>(contrato, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<?> todosContratos = this.contratoService.findAll();
        return todosContratos.isEmpty() ? new ResponseEntity<>("Contratos não encontrados", HttpStatus.NOT_FOUND) : new ResponseEntity<>(todosContratos, HttpStatus.OK);
    }

    @GetMapping("/detailed")
    public ResponseEntity<?> findAllDetailedContratos() {
        List<ContratoDTO> todosContratos = this.contratoService.findAllDetailedContratos();
        return todosContratos.isEmpty() ? new ResponseEntity<>("Contratos não encontrados", HttpStatus.NOT_FOUND) : new ResponseEntity<>(todosContratos, HttpStatus.OK);
    }
}