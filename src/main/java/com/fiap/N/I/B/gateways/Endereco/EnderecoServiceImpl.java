package com.fiap.N.I.B.gateways.Endereco;

import com.fiap.N.I.B.domains.Endereco;
import com.fiap.N.I.B.domains.Funcionario;
import com.fiap.N.I.B.domains.UsuarioGlobal;
import com.fiap.N.I.B.gateways.Repositories.EnderecoRepository;
import com.fiap.N.I.B.gateways.requests.EnderecoPatch;
import com.fiap.N.I.B.gateways.responses.EnderecoPostResponse;
import com.fiap.N.I.B.usecases.Endereco.*;
import com.fiap.N.I.B.gateways.Repositories.FuncionarioRepository;
import com.fiap.N.I.B.gateways.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoDeletar, EnderecoAtualiarParcial, EnderecoAtualizarTotalmente, EnderecoTodos, EnderecoPorFuncionario, EnderecoCriar, EnderecoPorUsuario {

    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;


    @Override
    public Optional<Endereco> atualizarParcial(String idPessoa, EnderecoPatch enderecoPatch) {
        Optional<Endereco> enderecoExistenteUsuario = enderecoRepository.findByUsuarioGlobal_CpfUser(idPessoa);
        Optional<Endereco> enderecoExistenteProfissional = enderecoRepository.findByFuncionario_IdFuncionario(Long.valueOf(idPessoa));

        if (enderecoExistenteUsuario.isPresent()) {
            Endereco enderecoAtualizado = enderecoExistenteUsuario.get();

            if (enderecoPatch.getRuaEndereco() != null) {
                enderecoAtualizado.setRuaEndereco(enderecoPatch.getRuaEndereco());
            }
            if (enderecoPatch.getNumeroEndereco() != null) {
                enderecoAtualizado.setNumeroEndereco(enderecoPatch.getNumeroEndereco());
            }
            if (enderecoPatch.getComplementoEndereco() != null) {
                enderecoAtualizado.setComplementoEndereco(enderecoPatch.getComplementoEndereco());
            }
            if (enderecoPatch.getBairroEndereco() != null) {
                enderecoAtualizado.setBairroEndereco(enderecoPatch.getBairroEndereco());
            }
            if (enderecoPatch.getCidadeEndereco() != null) {
                enderecoAtualizado.setCidadeEndereco(enderecoPatch.getCidadeEndereco());
            }
            if (enderecoPatch.getCepEndereco() != null) {
                enderecoAtualizado.setCepEndereco(enderecoPatch.getCepEndereco());
            }
            if (enderecoPatch.getEstadoEndereco() != null) {
                enderecoAtualizado.setEstadoEndereco(enderecoPatch.getEstadoEndereco());
            }

            return Optional.of(enderecoRepository.save(enderecoAtualizado));
        } else if (enderecoExistenteProfissional.isPresent()) {

            Endereco enderecoAtualizado = enderecoExistenteProfissional.get();

            if (enderecoPatch.getRuaEndereco() != null) {
                enderecoAtualizado.setRuaEndereco(enderecoPatch.getRuaEndereco());
            }
            if (enderecoPatch.getNumeroEndereco() != null) {
                enderecoAtualizado.setNumeroEndereco(enderecoPatch.getNumeroEndereco());
            }
            if (enderecoPatch.getComplementoEndereco() != null) {
                enderecoAtualizado.setComplementoEndereco(enderecoPatch.getComplementoEndereco());
            }
            if (enderecoPatch.getBairroEndereco() != null) {
                enderecoAtualizado.setBairroEndereco(enderecoPatch.getBairroEndereco());
            }
            if (enderecoPatch.getCidadeEndereco() != null) {
                enderecoAtualizado.setCidadeEndereco(enderecoPatch.getCidadeEndereco());
            }
            if (enderecoPatch.getCepEndereco() != null) {
                enderecoAtualizado.setCepEndereco(enderecoPatch.getCepEndereco());
            }
            if (enderecoPatch.getEstadoEndereco() != null) {
                enderecoAtualizado.setEstadoEndereco(enderecoPatch.getEstadoEndereco());
            }
            return Optional.of(enderecoRepository.save(enderecoAtualizado));
        }

        else{
            return  Optional.empty();
        }
    }

    @Override
    public Optional<Endereco> atualizarTotalmente(String idPessoa, Endereco endereco) {
        Optional<Endereco> enderecoExistenteUsuario = enderecoRepository.findByUsuarioGlobal_CpfUser(idPessoa);
        Optional<Endereco> enderecoExistenteProfissional = enderecoRepository.findByFuncionario_IdFuncionario(Long.valueOf(idPessoa));

        if (enderecoExistenteUsuario.isPresent()) {
            Endereco enderecoAtualizado = enderecoExistenteUsuario.get();

            enderecoAtualizado.setRuaEndereco(endereco.getRuaEndereco());
            enderecoAtualizado.setNumeroEndereco(endereco.getNumeroEndereco());
            enderecoAtualizado.setComplementoEndereco(endereco.getComplementoEndereco());
            enderecoAtualizado.setBairroEndereco(endereco.getBairroEndereco());
            enderecoAtualizado.setCidadeEndereco(endereco.getCidadeEndereco());
            enderecoAtualizado.setCepEndereco(endereco.getCepEndereco());
            enderecoAtualizado.setEstadoEndereco(endereco.getEstadoEndereco());
            return Optional.of(enderecoRepository.save(enderecoAtualizado));

        } else if (enderecoExistenteProfissional.isPresent()) {
            Endereco enderecoAtualizado = enderecoExistenteProfissional.get();

            enderecoAtualizado.setRuaEndereco(endereco.getRuaEndereco());
            enderecoAtualizado.setNumeroEndereco(endereco.getNumeroEndereco());
            enderecoAtualizado.setComplementoEndereco(endereco.getComplementoEndereco());
            enderecoAtualizado.setBairroEndereco(endereco.getBairroEndereco());
            enderecoAtualizado.setCidadeEndereco(endereco.getCidadeEndereco());
            enderecoAtualizado.setCepEndereco(endereco.getCepEndereco());
            enderecoAtualizado.setEstadoEndereco(endereco.getEstadoEndereco());
        }
        return Optional.empty();
    }

    public EnderecoPostResponse criarEndereco(String idPessoa, Endereco endereco) {
        Optional<UsuarioGlobal> usuario = usuarioRepository.findByCpfUser(idPessoa);
        Optional<Funcionario> profissional = funcionarioRepository.findFuncionarioByIdFuncionario(Long.valueOf(idPessoa));

        if (usuario.isPresent()) {
            endereco.setUsuarioGlobal(usuario.get());
            Endereco enderecoSalvo = enderecoRepository.save(endereco); // Salva o endereço

            UsuarioGlobal usuarioGlobalAtualizado = usuario.get();
            usuarioGlobalAtualizado.setEndereco(enderecoSalvo);
            usuarioRepository.save(usuarioGlobalAtualizado);

            return new EnderecoPostResponse("Endereço criado e atribuído ao usuário com sucesso.", enderecoSalvo);
            }
        else if (profissional.isPresent()) {
            endereco.setFuncionario(profissional.get());
            Endereco enderecoSalvo = enderecoRepository.save(endereco);

            Funcionario funcionarioAtualizado = profissional.get();
            funcionarioAtualizado.setEndereco(enderecoSalvo);
            funcionarioRepository.save(funcionarioAtualizado);

        } else {
            return new EnderecoPostResponse("Usuário ou Profissional não encontrado.", null);
        }

        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return new EnderecoPostResponse("Endereço criado com sucesso.", enderecoSalvo);
    }

    @Override
    public boolean deletarEndereco(String idPessoa) {
        Optional<Endereco> enderecoExistenteUsuario = enderecoRepository.findByUsuarioGlobal_CpfUser(idPessoa);
        Optional<Endereco> enderecoExistenteProfissional = enderecoRepository.findByFuncionario_IdFuncionario(Long.valueOf(idPessoa));

        if(enderecoExistenteUsuario.isPresent()){
            Optional<UsuarioGlobal> usuario = usuarioRepository.findByCpfUser(idPessoa);
            UsuarioGlobal usuarioGlobal = usuario.get();
            usuarioGlobal.setEndereco(null);
            usuarioRepository.save(usuarioGlobal);

            return enderecoRepository.findByUsuarioGlobal_CpfUser(idPessoa)
                    .map(enderecoDelete -> {
                        enderecoRepository.delete(enderecoDelete);
                        return true;
                    }).orElse(false);
        } else {
            Optional<Funcionario> funcinario = funcionarioRepository.findFuncionarioByIdFuncionario(Long.valueOf(idPessoa));
            Funcionario funcionarioExistente = funcinario.get();
            funcionarioExistente.setEndereco(null);
            funcionarioRepository.save(funcionarioExistente);
            return enderecoRepository.findByFuncionario_IdFuncionario(Long.valueOf(idPessoa))
                    .map(enderecoDelete -> {
                        enderecoRepository.delete(enderecoDelete);
                        return true;
                    }).orElse(false);
        }

    }

    @Override
    public Optional<Endereco> buscarEnderecoPorFuncionario(String idFuncionario) {
        return enderecoRepository.findByFuncionario_IdFuncionario(Long.valueOf(idFuncionario));
    }

    @Override
    public Optional<Endereco> buscarEnderecoPorUsuario(String cpfUser) {
        return enderecoRepository.findByUsuarioGlobal_CpfUser(cpfUser);
    }

    @Override
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }
}
