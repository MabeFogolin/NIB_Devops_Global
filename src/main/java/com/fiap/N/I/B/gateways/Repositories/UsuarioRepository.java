    package com.fiap.N.I.B.gateways.Repositories;

    import com.fiap.N.I.B.domains.UsuarioGlobal;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Optional;

    public interface UsuarioRepository extends JpaRepository<UsuarioGlobal, String> {

        Optional<UsuarioGlobal> findByCpfUser(String cpf);
        Page<UsuarioGlobal> findUsuariosByDataNascimentoUser(LocalDate dataNascimentoUser, Pageable pageable);
        List<UsuarioGlobal> findUsuariosByDataNascimentoUser(LocalDate dataNascimentoUser);
    }
