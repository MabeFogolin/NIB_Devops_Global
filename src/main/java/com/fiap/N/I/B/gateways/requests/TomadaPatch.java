package com.fiap.N.I.B.gateways.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TomadaPatch {

    private String nomeTomada;
    private String voltagem;
}