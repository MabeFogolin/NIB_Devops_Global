package com.fiap.N.I.B.gateways.responses;

import com.fiap.N.I.B.domains.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class EnderecoPostResponse {

    @NotNull
    private String mensagem;

    private Endereco endereco;


}
