package com.fiap.N.I.B.gateways.Repositories;

import com.fiap.N.I.B.domains.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {

    Optional<Endereco> findByUsuarioGlobal_CpfUser(String cpfUser);

    Optional<Endereco> findByFuncionario_IdFuncionario(Long idFuncionario);

}
