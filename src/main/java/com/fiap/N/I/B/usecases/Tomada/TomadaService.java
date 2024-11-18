package com.fiap.N.I.B.usecases.Tomada;

import com.fiap.N.I.B.domains.Tomada;
import com.fiap.N.I.B.gateways.requests.TomadaPatch;
import com.fiap.N.I.B.gateways.responses.TomadaPostResponse;

import java.util.List;
import java.util.Optional;

public interface TomadaService {

    TomadaPostResponse criarTomada(String cpfUser, Tomada tomada);

    Optional<Tomada> buscarTomadaPorId(int id);

    List<Tomada> buscarTodasTomadas();

    Optional<Tomada> atualizarTomada(int id, Tomada tomada);

    boolean removerTomada(int id);

    Optional<Tomada> atualizarInfo(int id, TomadaPatch tomadaPatch);

    List<Tomada> buscarTodasPorUsuario( String cpfUser);


}
