package com.fiap.N.I.B.gateways.Repositories;

import com.fiap.N.I.B.domains.Tomada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TomadaRepository extends JpaRepository<Tomada, Integer> {

    List<Tomada> findByUsuario_CpfUser(String nome);
}
