package com.fiap.N.I.B.gateways.responses;

import com.fiap.N.I.B.domains.Tomada;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TomadaPostResponse {

    private String mensagem;
    private Tomada tomada;

}
