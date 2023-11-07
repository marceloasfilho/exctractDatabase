package com.github.marceloasfilho.extractdatabase.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ObraDTO {
    ArrayList<BoletimMedicaoDTO> boletinsMedicao = new ArrayList<>();
    private Integer obraId;
    private String nome;
}