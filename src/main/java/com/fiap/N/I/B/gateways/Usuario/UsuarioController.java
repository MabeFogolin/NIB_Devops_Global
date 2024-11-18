package com.fiap.N.I.B.gateways.Usuario;

import com.fiap.N.I.B.domains.UsuarioGlobal;
import com.fiap.N.I.B.gateways.requests.UsuarioPatch;
import com.fiap.N.I.B.gateways.responses.UsuarioPostResponse;
import com.fiap.N.I.B.usecases.Usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Busca um usuário por CPF", description = "Retorna um usuário específico baseado no CPF fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<EntityModel<UsuarioGlobal>> buscarPorCpf(@PathVariable String cpf) {
        Optional<UsuarioGlobal> usuario = usuarioService.buscarPorCpf(cpf);

        return usuario.map(u -> {
            EntityModel<UsuarioGlobal> resource = EntityModel.of(u);
            Link selfLink = linkTo(methodOn(UsuarioController.class).buscarPorCpf(cpf)).withSelfRel();
            Link allUsersLink = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("all-users");
            resource.add(selfLink);
            resource.add(allUsersLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Busca todos os usuários", description = "Traz todos os usuários cadastrados, com os links atribuídos individualmente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", links = {
                    @io.swagger.v3.oas.annotations.links.Link(name = "teste", operationRef = "GET")
            }),
            @ApiResponse(responseCode = "404")
    })
    @GetMapping("/todos")
    public ResponseEntity<List<EntityModel<UsuarioGlobal>>> buscarUsuarios() {
        List<UsuarioGlobal> usuarioGlobals = usuarioService.buscarTodos();

        List<EntityModel<UsuarioGlobal>> todosUsuariosComLink = usuarioGlobals.stream().map(usuario -> {
            EntityModel<UsuarioGlobal> resource = EntityModel.of(usuario);
            Link selfLink = linkTo(methodOn(UsuarioController.class).buscarPorCpf(usuario.getCpfUser())).withSelfRel();
            Link allUsersLink = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("all-users");

            resource.add(selfLink);
            resource.add(allUsersLink);

            return resource;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(todosUsuariosComLink);
    }

    @Operation(summary = "Busca usuários por data de nascimento", description = "Retorna uma lista de usuários baseados na data de nascimento fornecida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
    })
    @GetMapping("/nascimento/{dataNascimento}")
    public ResponseEntity<List<EntityModel<UsuarioGlobal>>> getUsuariosPorDataNascimento(@PathVariable LocalDate dataNascimento) {
        List<UsuarioGlobal> usuarioGlobals = usuarioService.buscarPorDataNascimentoEmLista(dataNascimento);

        List<EntityModel<UsuarioGlobal>> usuariosComLinks = usuarioGlobals.stream().map(usuario -> {
            EntityModel<UsuarioGlobal> resource = EntityModel.of(usuario);
            Link selfLink = linkTo(methodOn(UsuarioController.class).buscarPorCpf(usuario.getCpfUser())).withSelfRel();
            Link allUsersLink = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("all-users");

            resource.add(selfLink);
            resource.add(allUsersLink);

            return resource;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(usuariosComLinks);
    }

    @Operation(summary = "Cria um novo usuário", description = "Adiciona um novo usuário ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping("/criar")
    public ResponseEntity<EntityModel<UsuarioPostResponse>> criarUsuario(@RequestBody UsuarioGlobal usuarioGlobal) {
        UsuarioPostResponse respostaCriacao = usuarioService.criarUsuario(usuarioGlobal);

        EntityModel<UsuarioPostResponse> resource = EntityModel.of(respostaCriacao);
        Link selfLink = linkTo(methodOn(UsuarioController.class).buscarPorCpf(usuarioGlobal.getCpfUser())).withSelfRel();
        Link allUsersLink = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("all-users");

        resource.add(selfLink);
        resource.add(allUsersLink);
        if ("CPF já cadastrado no sistema".equals(respostaCriacao.getMensagem())) {
            return ResponseEntity.status(409).body(resource);
        }
        return ResponseEntity.status(201).body(resource);
    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza informações de um usuário específico baseado em dados parciais fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<EntityModel<UsuarioGlobal>> atualizarUsuario(@PathVariable String cpf, @RequestBody UsuarioGlobal usuarioGlobalAtualizado) {
        Optional<UsuarioGlobal> usuario = usuarioService.atualizarUsuario(cpf, usuarioGlobalAtualizado);

        return usuario.map(u -> {
            EntityModel<UsuarioGlobal> resource = EntityModel.of(u);
            Link selfLink = linkTo(methodOn(UsuarioController.class).buscarPorCpf(cpf)).withSelfRel();
            Link allUsersLink = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("all-users");

            resource.add(selfLink);
            resource.add(allUsersLink);

            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza o email do usuário", description = "Atualiza informações de um usuário específico baseado em dados parciais fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PatchMapping("/{cpfUser}/atualizar")
    public ResponseEntity<EntityModel<UsuarioGlobal>> atualizarPlanoEmail(
            @PathVariable String cpfUser,
            @RequestBody @Valid UsuarioPatch userEmailPlano) {

        Optional<UsuarioGlobal> usuarioAtualizado = usuarioService.atualizarEmailPlano(cpfUser, userEmailPlano);

        return usuarioAtualizado.map(u -> {
            EntityModel<UsuarioGlobal> resource = EntityModel.of(u);
            Link selfLink = linkTo(methodOn(UsuarioController.class).buscarPorCpf(cpfUser)).withSelfRel();
            Link allUsersLink = linkTo(methodOn(UsuarioController.class).buscarUsuarios()).withRel("all-users");

            resource.add(selfLink);
            resource.add(allUsersLink);

            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Deleta um usuário", description = "Deleta o usuário com base no CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String cpf) {
        boolean deleted = usuarioService.deletarUsuario(cpf);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Recupera usuários com base na data de nascimento de maneira pageada", description = "Recupera usuários com base na data de nascimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrado")
    })
    @GetMapping("/nascimento")
    public ResponseEntity<Page<UsuarioGlobal>> buscarPorDataNascimento(@RequestParam LocalDate dataNascimentoUser, Pageable page) {
        Page<UsuarioGlobal> usuarios = usuarioService.buscarPorDataNascimentoPaginado(dataNascimentoUser, page);
        return ResponseEntity.ok(usuarios);
    }

}
