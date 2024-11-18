package com.fiap.N.I.B.gateways.responses;

import com.fiap.N.I.B.domains.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioPostResponse {

    private String mensagem;
    private Funcionario funcionario;

}
