package com.github.marceloasfilho.extractdatabase.model;

import jakarta.persistence.*;

@Entity
@Embeddable
@SqlResultSetMapping(name = "ContratoMapping", classes = {
        @ConstructorResult(targetClass = Contrato.class,
                columns = {
                        @ColumnResult(name = "ID do Contrato", type = Integer.class),
                        @ColumnResult(name = "Nome do Contrato", type = String.class)
                }
        ),
        @ConstructorResult(targetClass = Obra.class,
                columns = {
                        @ColumnResult(name = "ID da Obra", type = Integer.class),
                        @ColumnResult(name = "Nome da Obra", type = String.class)
                }
        ),
        @ConstructorResult(targetClass = BoletimMedicao.class,
                columns = {
                        @ColumnResult(name = "ID do Boletim de Medição", type = Integer.class),
                        @ColumnResult(name = "Nome do Boletim de Medição", type = String.class)
                }
        ),
        @ConstructorResult(targetClass = Item.class,
                columns = {
                        @ColumnResult(name = "ID do Item", type = Integer.class),
                        @ColumnResult(name = "Nome do Item", type = String.class)
                }
        )
})
public class SQLDummyMappingResultToDTO {
    @Id
    int id;
}