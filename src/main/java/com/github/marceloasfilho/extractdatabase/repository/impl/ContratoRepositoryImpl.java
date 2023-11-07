package com.github.marceloasfilho.extractdatabase.repository.impl;

import com.github.marceloasfilho.extractdatabase.dto.ContratoDTO;
import com.github.marceloasfilho.extractdatabase.model.Contrato;
import com.github.marceloasfilho.extractdatabase.repository.ContratoRepository;
import com.github.marceloasfilho.extractdatabase.utils.ContratoRepositoryUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratoRepositoryImpl implements ContratoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Contrato findContratoById(Integer contratoId) {
        Query nativeQuery = this.entityManager.createNativeQuery("SELECT id_contrato, nome FROM contrato WHERE id_contrato =:id_de_contrato");
        nativeQuery.setParameter("id_de_contrato", contratoId);

        return ContratoRepositoryUtils.obterContrato(nativeQuery.getSingleResult());
    }

    @Override
    public List<?> findAll() {
        return this.entityManager.createNativeQuery("SELECT * FROM contrato").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ContratoDTO> findAllContratosDetalhados() {
        Query nativeQuery = this.entityManager.createNativeQuery(
                "SELECT " +
                        "contrato.id_contrato AS " + "\"ID do Contrato\", " +
                        "contrato.nome AS \"Nome do Contrato\", " +
                        "obra.id_obra AS \"ID da Obra\", " +
                        "obra.nome AS \"Nome da Obra\", " +
                        "boletim_medicao.id_boletim_medicao AS \"ID do Boletim de Medição\", " +
                        "boletim_medicao.nome AS \"Nome do Boletim de Medição\", " +
                        "item.item_id AS \"ID do Item\", " +
                        "item.nome AS \"Nome do Item\" " +
                        "FROM " +
                        "contrato " +
                        "INNER JOIN obra ON contrato.id_contrato = obra.contrato_id " +
                        "INNER JOIN boletim_medicao ON obra.id_obra = boletim_medicao.obra_id " +
                        "INNER JOIN item ON boletim_medicao.id_boletim_medicao = item.boletim_medicao_id", "ContratoMapping");

        return ContratoRepositoryUtils.obterContratosSumarizados(nativeQuery.getResultList());
    }
}