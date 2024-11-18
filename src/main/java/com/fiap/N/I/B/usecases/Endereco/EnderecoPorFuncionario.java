package com.fiap.N.I.B.usecases.Endereco;

import com.fiap.N.I.B.domains.Endereco;

import java.util.Optional;

public interface EnderecoPorFuncionario {

    Optional<Endereco> buscarEnderecoPorFuncionario(String idFuncionario);
}
