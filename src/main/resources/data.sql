DROP TABLE IF EXISTS boletim_medicao;
CREATE TABLE boletim_medicao (
                                 id_boletim_medicao bigint NOT NULL,
                                 nome varchar(50) DEFAULT NULL,
                                 obra_id bigint DEFAULT NULL
) ;
INSERT INTO boletim_medicao (id_boletim_medicao, nome, obra_id)
VALUES (1, 'Medição 1', 1),
       (2, 'Medição 2', 2),
       (3, 'Medição 3', 3),
       (4, 'Medição 4', 4),
       (5, 'Medição 5', 5),
       (6, 'Medição 6', 6);
DROP TABLE IF EXISTS contrato;
CREATE TABLE contrato (
                          id_contrato bigint NOT NULL,
                          nome varchar(50) DEFAULT NULL
) ;
INSERT INTO contrato (id_contrato, nome)
VALUES (1, 'Licitação'),
       (2, 'Mudança'),
       (3, 'Construção');
DROP TABLE IF EXISTS item;
CREATE TABLE item (
                      item_id bigint NOT NULL,
                      nome varchar(50) NOT NULL,
                      boletim_medicao_id bigint DEFAULT NULL
) ;
INSERT INTO item (item_id, nome, boletim_medicao_id)
VALUES (1, 'Pá', 1),
       (2, 'Peneira', 2),
       (3, 'Carro de mão', 3),
       (4, 'Parafuso', 4),
       (5, 'Martelo', 5),
       (6, 'Capacete', 6);
DROP TABLE IF EXISTS obra;
CREATE TABLE obra (
                      id_obra bigint NOT NULL,
                      nome varchar(50) DEFAULT NULL,
                      contrato_id bigint DEFAULT NULL
) ;
INSERT INTO obra (id_obra, nome, contrato_id)
VALUES (1, 'Reparo Viaduto', 1),
       (2, 'Construir Laje', 1),
       (3, 'Instalar Hidrante', 2),
       (4, 'Pintar Faixa de Pedestre', 2),
       (5, 'Corrigir Semáforo', 3),
       (6, 'Consertar Parada de Ônibus', 3);