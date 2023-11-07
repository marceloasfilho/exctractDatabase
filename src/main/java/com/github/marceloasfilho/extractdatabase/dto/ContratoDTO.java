package com.github.marceloasfilho.extractdatabase.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ContratoDTO {
    private Integer id;
    private String nome;
    private ArrayList<ObraDTO> obras = new ArrayList<>();
}
