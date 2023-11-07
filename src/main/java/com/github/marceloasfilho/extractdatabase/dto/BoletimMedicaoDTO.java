package com.github.marceloasfilho.extractdatabase.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BoletimMedicaoDTO {
    ArrayList<ItemDTO> itens = new ArrayList<>();
    private Integer boletimMedicaoId;
    private String nome;
}
