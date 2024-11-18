package com.fiap.N.I.B.gateways.Repositories;

import com.fiap.N.I.B.domains.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

    Optional<Funcionario> findFuncionarioByIdFuncionario(Long idFuncionario);
    Optional<Funcionario> findFuncionarioByEmailFuncionario(String email);

    @Procedure(procedureName = "deletar_funcionario_procedure")
    void deletarFuncionarioProcedure(Long idFuncionario);
}
