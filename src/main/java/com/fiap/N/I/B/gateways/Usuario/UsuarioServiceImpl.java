package com.fiap.N.I.B.gateways.Usuario;

import com.fiap.N.I.B.domains.UsuarioGlobal;
import com.fiap.N.I.B.gateways.requests.UsuarioPatch;
import com.fiap.N.I.B.gateways.responses.UsuarioPostResponse;
import com.fiap.N.I.B.usecases.Usuario.UsuarioService;
import com.fiap.N.I.B.gateways.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioPostResponse criarUsuario(UsuarioGlobal usuarioGlobalEntrada) {
        Optional<UsuarioGlobal> usuarioBusca = usuarioRepository.findByCpfUser(usuarioGlobalEntrada.getCpfUser());
        if (usuarioBusca.isEmpty()) {
            usuarioRepository.save(usuarioGlobalEntrada);
            return new UsuarioPostResponse("Novo usuário cadastrado", usuarioGlobalEntrada);
        } else {
            return new UsuarioPostResponse("CPF já cadastrado no sistema", usuarioBusca.get());
        }
    }

    @Override
    public Optional<UsuarioGlobal> buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpfUser(cpf);
    }

    @Override
    public List<UsuarioGlobal> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Page<UsuarioGlobal> buscarPorDataNascimentoPaginado(LocalDate dataNascimentoUser, Pageable pageable) {
        return usuarioRepository.findUsuariosByDataNascimentoUser(dataNascimentoUser, pageable);
    }


    @Override
    public List<UsuarioGlobal> buscarPorDataNascimentoEmLista(LocalDate dataNascimentoUser) {
        return usuarioRepository.findUsuariosByDataNascimentoUser(dataNascimentoUser);
    }

    @Override
    public Optional<UsuarioGlobal> atualizarUsuario(String cpf, UsuarioGlobal usuarioGlobalAtualizado) {
        return usuarioRepository.findByCpfUser(cpf)
                .map(usuario -> {
                    usuario.setNomeUser(usuarioGlobalAtualizado.getNomeUser());
                    usuario.setSobrenomeUser(usuarioGlobalAtualizado.getSobrenomeUser());
                    usuario.setTelefoneUser(usuarioGlobalAtualizado.getTelefoneUser());
                    usuario.setDataNascimentoUser(usuarioGlobalAtualizado.getDataNascimentoUser());
                    usuario.setEmailUser(usuarioGlobalAtualizado.getEmailUser());
                    return usuarioRepository.save(usuario);
                });
    }

    @Override
    public Optional<UsuarioGlobal> atualizarEmailPlano(String cpf, UsuarioPatch usuarioNovoEmailPlano) {
        Optional<UsuarioGlobal> usuarioExistente = usuarioRepository.findByCpfUser(cpf);
        if (usuarioExistente.isPresent()) {
            UsuarioGlobal usuarioGlobalNovo = usuarioExistente.get();
            usuarioGlobalNovo.setEmailUser(usuarioNovoEmailPlano.getEmailUser());
            UsuarioGlobal usuarioGlobalAtualizado = usuarioRepository.save(usuarioGlobalNovo);
            return Optional.of(usuarioGlobalAtualizado);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deletarUsuario(String cpf) {
        return usuarioRepository.findByCpfUser(cpf)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return true;
                }).orElse(false);
    }
}
