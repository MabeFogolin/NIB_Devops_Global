package com.fiap.N.I.B.usecases.Funcionario;

import com.fiap.N.I.B.domains.Funcionario;
import com.fiap.N.I.B.gateways.requests.FuncionarioPatch;
import com.fiap.N.I.B.gateways.responses.FuncionarioPostResponse;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    FuncionarioPostResponse criarFuncionario(Funcionario funcionarioParaCriar);

    Optional<Funcionario> buscarFuncionario(String idFuncionario);

    List<Funcionario> buscarTodos();

    Optional<Funcionario> atualizarFuncionario(String idFuncionario, Funcionario funcionarioParaAtualizar);

    boolean deletarFuncionario(String idFuncionario);

    Optional<Funcionario> atualizarEmailTelefone(String idFuncionario, FuncionarioPatch funcionarioPatch);

    boolean deletarFuncionarioProcedure(Long idFuncionario);
}
