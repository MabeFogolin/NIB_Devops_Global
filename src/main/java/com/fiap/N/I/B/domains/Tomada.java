package com.fiap.N.I.B.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tomada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTomada;

    @NotNull
    @Size(max = 50, message = "Nome da tomada não pode ter mais que 25 caracteres.")
    private String nomeTomada;

    @Column(name = "dia_contagem")
    private java.sql.Date diaContagem;

    @Min(value = 0, message = "O valor mínimo de quantidade gasta é 0.")
    @Max(value = 99999, message = "O valor máximo de quantidade gasta é 99999.")
    private Double qtdGasta;

    @Min(value = 0, message = "A tarifa mínima permitida é 0.")
    @Max(value = 9, message = "A tarifa máxima permitida é 9.")
    private Double tarifaEletricidade;

    @NotNull
    private String voltagem;

    @JsonIgnore
    @ManyToOne
    private UsuarioGlobal usuario;

}
