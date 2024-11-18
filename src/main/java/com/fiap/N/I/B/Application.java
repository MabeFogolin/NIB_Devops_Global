package com.fiap.N.I.B;

import com.fiap.N.I.B.domains.Endereco;
import com.fiap.N.I.B.domains.Funcionario;
import com.fiap.N.I.B.domains.Tomada;
import com.fiap.N.I.B.domains.UsuarioGlobal;
import com.fiap.N.I.B.gateways.Repositories.EnderecoRepository;
import com.fiap.N.I.B.gateways.Repositories.FuncionarioRepository;
import com.fiap.N.I.B.gateways.Repositories.TomadaRepository;
import com.fiap.N.I.B.gateways.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

	private final UsuarioRepository usuarioRepository;
	private final EnderecoRepository enderecoRepository;
	private final TomadaRepository tomadaRepository;
	private final FuncionarioRepository funcionarioRepository;


    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@EventListener(value = ApplicationReadyEvent.class)
	public void setupUsuarios() {
		List<String> cpfs = List.of(
				"22832168000",
				"15263085095",
				"10932983081",
				"91072223007",
				"69253989050"
		);

		LocalDate now = LocalDate.now();

		for (int i = 0; i < cpfs.size(); i++) {

			// Criando o usuário
			UsuarioGlobal usuario = UsuarioGlobal.builder()
					.cpfUser(cpfs.get(i))
					.nomeUser("Nome " + i)
					.sobrenomeUser("Sobrenome " + i)
					.telefoneUser("1198765432" + i % 10)
					.dataNascimentoUser(now.minusYears(20 + i))
					.emailUser("email" + i + "@example.com")
					.tomadas(new ArrayList<>())  // Lista de tomadas
					.build();
			UsuarioGlobal usuarioSalvo = usuarioRepository.save(usuario);

			// Criando o endereço
			Endereco endereco = Endereco.builder()
					.ruaEndereco("Rua " + i)
					.numeroEndereco(100 + i)  // Número do endereço
					.complementoEndereco(i % 2 == 0 ? "Apto " + i : null)  // Complemento (pode ser nulo)
					.bairroEndereco("Bairro " + i)
					.cidadeEndereco("Cidade " + i)
					.cepEndereco("12345-0" + i)  // Formato de CEP
					.estadoEndereco("SP")  // Estado fixo
					.build();

			endereco.setUsuarioGlobal(usuarioSalvo);
			enderecoRepository.save(endereco);

			// Atualizando o usuário com o endereço
			usuario.setEndereco(endereco);
			usuarioRepository.save(usuario);

			// Criando o funcionário (relacionado ao usuário)
			Funcionario funcionario = Funcionario.builder()
					.nomeFuncionario("Funcionario " + i)
					.sobrenomeFuncionario("Sobrenome " + i)
					.telefoneFuncionario("1198765432" + i % 10)
					.emailFuncionario("funcionario" + i + "@example.com")
					.endereco(endereco)  // Associando o endereço ao funcionário
					.build();
			funcionarioRepository.save(funcionario);

			// Criando a tomada (relacionada ao usuário)
			Tomada tomada = Tomada.builder()
					.nomeTomada("Tomada " + ( i + 1))
					.diaContagem(java.sql.Date.valueOf(now.minusDays(i)))
					.qtdGasta(10.0 + i)
					.tarifaEletricidade(0.50 + i * 0.1)
					.voltagem("110V")
					.usuario(usuarioSalvo)
					.build();
			tomadaRepository.save(tomada);

			usuario.getTomadas().add(tomada);
			usuarioRepository.save(usuario);
		}

		System.out.println("Usuários, endereços, funcionários e tomadas criados e associados com sucesso.");
	}



}
