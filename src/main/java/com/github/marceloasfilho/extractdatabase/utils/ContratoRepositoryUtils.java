package com.github.marceloasfilho.extractdatabase.utils;

import com.github.marceloasfilho.extractdatabase.dto.BoletimMedicaoDTO;
import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.dto.ItemDTO;
import com.github.marceloasfilho.extractdatabase.dto.ObraDTO;
import com.github.marceloasfilho.extractdatabase.model.BoletimMedicao;
import com.github.marceloasfilho.extractdatabase.model.Contrato;
import com.github.marceloasfilho.extractdatabase.model.Item;
import com.github.marceloasfilho.extractdatabase.model.Obra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ContratoRepositoryUtils {
    private ContratoRepositoryUtils() {
    }

    public static Contrato obterContrato(Object queryResult) {
        Object[] rows = (Object[]) queryResult;
        return new Contrato(((Long) rows[0]).intValue(), (String) rows[1]);
    }

    public static List<ContratoDTO> obterContratosSumarizados(List<Object[]> rows) {
        List<ContratoDTO> contratos = new ArrayList<>();

        while (!rows.isEmpty()) {
            ContratoDTO contratoAtual = new ContratoDTO();

            ContratoDTO contratoInicial = new ContratoDTO();
            contratoInicial.setId(((Contrato) (rows.get(0)[0])).getContratoId());
            contratoInicial.setNome(((Contrato) (rows.get(0)[0])).getNome());

            contratoAtual.setId(contratoInicial.getId());
            contratoAtual.setNome(contratoInicial.getNome());

            ArrayList<Object[]> contratosComMesmoId = rows.stream().filter(contrato -> Objects.equals(((Contrato) contrato[0]).getContratoId(), contratoInicial.getId())).collect(Collectors
                    .toCollection(ArrayList::new));

            ArrayList<ObraDTO> obrasMesmoId = new ArrayList<>();

            for (Object[] contrato : contratosComMesmoId) {

                ObraDTO obraDTO = new ObraDTO();
                obraDTO.setObraId(((Obra) (contrato[1])).getObraId());
                obraDTO.setNome(((Obra) (contrato[1])).getNome());

                boolean obraFound = false;
                int indexObraFound = -1;
                for (int j = 0; j < obrasMesmoId.size(); j++) {
                    if (obrasMesmoId.get(j).getObraId().equals(obraDTO.getObraId())) {
                        obraFound = true;
                        indexObraFound = j;
                        break;
                    }
                }

                if (obraFound) {
                    obraDTO = obrasMesmoId.get(indexObraFound);

                    BoletimMedicaoDTO boletimMedicaoDTO = new BoletimMedicaoDTO();
                    boletimMedicaoDTO.setBoletimMedicaoId(((BoletimMedicao) (contrato[2])).getBoletimMedicaoId());
                    boletimMedicaoDTO.setNome(((BoletimMedicao) (contrato[2])).getNome());

                    boolean boletimMedicacaoFound = false;
                    int indexBoletimMedicacaoFound = -1;

                    for (int j = 0; j < obraDTO.getBoletinsMedicao().size(); j++) {
                        if (obraDTO.getBoletinsMedicao().get(j).getBoletimMedicaoId().equals(boletimMedicaoDTO.getBoletimMedicaoId())) {
                            boletimMedicacaoFound = true;
                            indexBoletimMedicacaoFound = j;
                            break;
                        }
                    }

                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setItemId(((Item) contrato[3]).getItemId());
                    itemDTO.setNome(((Item) contrato[3]).getNome());

                    if (boletimMedicacaoFound) {
                        obraDTO.getBoletinsMedicao().get(indexBoletimMedicacaoFound).getItens().add(itemDTO);
                    } else {
                        obraDTO.getBoletinsMedicao().add(boletimMedicaoDTO);
                        boletimMedicaoDTO.setItens(new ArrayList<>(List.of(itemDTO)));
                    }
                } else {
                    BoletimMedicaoDTO boletimMedicaoDTO = new BoletimMedicaoDTO();
                    boletimMedicaoDTO.setBoletimMedicaoId(((BoletimMedicao) (contrato[2])).getBoletimMedicaoId());
                    boletimMedicaoDTO.setNome(((BoletimMedicao) (contrato[2])).getNome());

                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setItemId(((Item) contrato[3]).getItemId());
                    itemDTO.setNome(((Item) contrato[3]).getNome());

                    boletimMedicaoDTO.setItens(new ArrayList<>(List.of(itemDTO)));
                    obraDTO.setBoletinsMedicao(new ArrayList<>(List.of(boletimMedicaoDTO)));
                    obrasMesmoId.add(obraDTO);
                }
            }
            contratoAtual.setObras(obrasMesmoId);
            contratos.add(contratoAtual);
            rows.removeAll(contratosComMesmoId);
        }
        return contratos;
    }
}
