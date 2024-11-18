package com.fiap.N.I.B.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedStoredProcedureQuery(
        name = "deletarFuncionarioProcedure",
        procedureName = "deletar_funcionario_procedure",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_funcionario", type =String.class)
        }
)
public class Funcionario extends RepresentationModel<Funcionario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    @NotNull
    @Size(max = 20, message = "Nome deve ter no máximo 20 caracteres")
    private String nomeFuncionario;

    @NotNull
    @Size(max = 30, message = "Sobrenome deve ter no máximo 30 caracteres")
    private String sobrenomeFuncionario;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "Telefone deve conter 11 dígitos numéricos")
    private String telefoneFuncionario;


    @Email(message = "Informe um e-mail válido")
    @Column(unique = true, nullable = false)
    private String emailFuncionario;

    @OneToOne
    private Endereco endereco;

}
