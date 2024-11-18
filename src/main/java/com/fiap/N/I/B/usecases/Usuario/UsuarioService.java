package com.fiap.N.I.B.usecases.Usuario;

import com.fiap.N.I.B.domains.UsuarioGlobal;
import com.fiap.N.I.B.gateways.requests.UsuarioPatch;
import com.fiap.N.I.B.gateways.responses.UsuarioPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    // Criar um novo usuário
    UsuarioPostResponse criarUsuario(UsuarioGlobal usuario);

    // Buscar um usuário pelo CPF
    Optional<UsuarioGlobal> buscarPorCpf(String cpf);

    // Buscar todos os usuários
    List<UsuarioGlobal> buscarTodos();
    ;

    // Buscar usuários por data de nascimento com paginação
    Page<UsuarioGlobal> buscarPorDataNascimentoPaginado(LocalDate dataNascimentoUser, Pageable pageable);

    // Buscar usuários por data de nascimento em lista
    List<UsuarioGlobal> buscarPorDataNascimentoEmLista(LocalDate dataNascimentoUser);

    // Atualizar um usuário existente
    Optional<UsuarioGlobal> atualizarUsuario(String cpf, UsuarioGlobal usuarioAtualizado);

    //Atualizar email e plano do usuário
    Optional<UsuarioGlobal> atualizarEmailPlano(String cpf, UsuarioPatch usuarioNovoEmailPlano);

    // Deletar um usuário
    boolean deletarUsuario(String cpf);
}