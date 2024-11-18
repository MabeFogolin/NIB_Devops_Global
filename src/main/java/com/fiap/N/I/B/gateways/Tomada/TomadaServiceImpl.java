package com.fiap.N.I.B.gateways.Tomada;

import com.fiap.N.I.B.domains.Tomada;
import com.fiap.N.I.B.domains.UsuarioGlobal;
import com.fiap.N.I.B.gateways.Repositories.TomadaRepository;
import com.fiap.N.I.B.gateways.Repositories.UsuarioRepository;
import com.fiap.N.I.B.gateways.requests.TomadaPatch;
import com.fiap.N.I.B.gateways.responses.TomadaPostResponse;
import com.fiap.N.I.B.usecases.Tomada.TomadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TomadaServiceImpl implements TomadaService {
    private final UsuarioRepository usuarioRepository;
    private final TomadaRepository tomadaRepository;

    @Override
    public TomadaPostResponse criarTomada(String cpfUser, Tomada tomada) {
        Optional<Tomada> tomadaExistente = tomadaRepository.findById(tomada.getIdTomada());
        Optional<UsuarioGlobal> usuario = usuarioRepository.findById(cpfUser);
        if (tomadaExistente.isPresent()) {
            return new TomadaPostResponse("Tomada não cadastrada, ID já atribuído", tomada);
        } else if (usuario.isEmpty()) {
            return new TomadaPostResponse("Tomada não cadastrada, Usuário não encontrado", null);
        }
        tomada.setUsuario(usuario.get());
        UsuarioGlobal usuarioGlobal = usuario.get();
        usuarioGlobal.setTomadas(new ArrayList<>());
        usuarioGlobal.getTomadas().add(tomada);

        tomadaRepository.save(tomada);
        return new TomadaPostResponse("Tomada cadastrada com sucesso", tomada);
    }

    @Override
    public Optional<Tomada> buscarTomadaPorId(int id) {
        return tomadaRepository.findById(id);
    }

    @Override
    public List<Tomada> buscarTodasTomadas() {
        return tomadaRepository.findAll();
    }


    @Override
    public Optional<Tomada> atualizarTomada(int id, Tomada tomada) {
        return tomadaRepository.findById(id).map(tomadaExistente -> {
            // Update fields of the existing 'Tomada' object
            tomadaExistente.setNomeTomada(tomada.getNomeTomada());
            tomadaExistente.setDiaContagem(tomada.getDiaContagem());
            tomadaExistente.setQtdGasta(tomadaExistente.getQtdGasta());
            tomadaExistente.setTarifaEletricidade(tomada.getTarifaEletricidade());
            return tomadaRepository.save(tomadaExistente);
        });
    }

    @Override
    public boolean removerTomada(int id) {
        Optional<Tomada> tomadaExistente = tomadaRepository.findById(id);
        if (tomadaExistente.isPresent()) {
            tomadaRepository.delete(tomadaExistente.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Tomada> atualizarInfo(int id, TomadaPatch tomadaPatch) {
        Optional<Tomada> tomadaBusca = tomadaRepository.findById(id);
        if (tomadaBusca.isPresent()) {
            Tomada tomadaAtualizada = tomadaBusca.get();
            if(tomadaPatch.getNomeTomada() != null) {
                tomadaAtualizada.setNomeTomada(tomadaPatch.getNomeTomada());
            }
            if (tomadaPatch.getVoltagem() != null) {
                tomadaAtualizada.setVoltagem(tomadaPatch.getVoltagem());
            }
            return Optional.of(tomadaRepository.save(tomadaAtualizada));
        }
        return Optional.empty();
    }

    @Override
    public List<Tomada> buscarTodasPorUsuario(String cpfUser) {
        return tomadaRepository.findByUsuario_CpfUser(cpfUser);
    }
}
