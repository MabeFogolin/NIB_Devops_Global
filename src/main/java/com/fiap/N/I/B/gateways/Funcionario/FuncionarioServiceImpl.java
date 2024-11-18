package com.fiap.N.I.B.gateways.Funcionario;

import com.fiap.N.I.B.domains.Funcionario;
import com.fiap.N.I.B.gateways.requests.FuncionarioPatch;
import com.fiap.N.I.B.gateways.responses.FuncionarioPostResponse;
import com.fiap.N.I.B.gateways.Repositories.FuncionarioRepository;
import com.fiap.N.I.B.usecases.Funcionario.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;


    @Override
    public FuncionarioPostResponse criarFuncionario(Funcionario funcionarioParaCriar) {
        Optional<Funcionario> profissionalBusca = funcionarioRepository.findFuncionarioByIdFuncionario(funcionarioParaCriar.getIdFuncionario());
        Optional<Funcionario> funcionarioBusca = funcionarioRepository.findFuncionarioByEmailFuncionario(funcionarioParaCriar.getEmailFuncionario());
        if(profissionalBusca.isEmpty() && funcionarioBusca.isEmpty()){
            funcionarioRepository.save(funcionarioParaCriar);
            return new FuncionarioPostResponse("Novo funcionario cadastrado", funcionarioParaCriar);
        } else if (funcionarioBusca.isPresent()) {
            Funcionario funcionario = funcionarioBusca.get();
            return new FuncionarioPostResponse("E-mail já cadastrado em sistema", funcionario);
        }
        return new FuncionarioPostResponse("Registro profissional já cadastrado no sistema", funcionarioParaCriar);
    }

    @Override
    public Optional<Funcionario> buscarFuncionario(String idFuncionario) {
        return funcionarioRepository.findFuncionarioByIdFuncionario(Long.valueOf(idFuncionario));
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> atualizarFuncionario(String idFuncionario, Funcionario funcionarioParaAtualizar) {
        return funcionarioRepository.findFuncionarioByIdFuncionario(Long.valueOf(idFuncionario)).map(funcionario ->{
            funcionario.setNomeFuncionario(funcionarioParaAtualizar.getNomeFuncionario());
            funcionario.setSobrenomeFuncionario(funcionarioParaAtualizar.getSobrenomeFuncionario());
            funcionario.setEmailFuncionario(funcionarioParaAtualizar.getEmailFuncionario());
            funcionario.setTelefoneFuncionario(funcionario.getTelefoneFuncionario());
            return  funcionarioRepository.save(funcionario);
        });
    }

    @Override
    public boolean deletarFuncionario(String registroProfissional) {
        return funcionarioRepository.findFuncionarioByIdFuncionario(Long.valueOf(registroProfissional))
                .map(profissional -> {
                    funcionarioRepository.delete(profissional);
                    return true;
                }).orElse(false);
    }

    @Override
    public Optional<Funcionario> atualizarEmailTelefone(String idFuncionario, FuncionarioPatch funcionarioPatch) {
        Optional<Funcionario> funcionarioExistente = funcionarioRepository.findFuncionarioByIdFuncionario(Long.valueOf(idFuncionario));

        if (funcionarioExistente.isPresent()) {
            Funcionario funcionarioNovo = funcionarioExistente.get();

            if (funcionarioPatch.getEmailFuncionario() != null) {
                funcionarioNovo.setEmailFuncionario(funcionarioPatch.getEmailFuncionario());
            }
            if (funcionarioPatch.getTelefoneFuncionario() != null) {
                funcionarioNovo.setTelefoneFuncionario(funcionarioPatch.getTelefoneFuncionario());
            }
            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioNovo);
            return Optional.of(funcionarioAtualizado);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deletarFuncionarioProcedure(Long idFuncionario) {
        Optional<Funcionario> profissional = funcionarioRepository.findFuncionarioByIdFuncionario(idFuncionario);
        if (profissional.isPresent()) {
            try {
                funcionarioRepository.deletarFuncionarioProcedure(idFuncionario);
                return true;
            } catch (Exception e) {
                System.err.println("Erro ao deletar profissional: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
}
