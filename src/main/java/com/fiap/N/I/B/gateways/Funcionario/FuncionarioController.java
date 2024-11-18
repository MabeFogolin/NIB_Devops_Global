package com.fiap.N.I.B.gateways.Funcionario;

import com.fiap.N.I.B.domains.Funcionario;
import com.fiap.N.I.B.gateways.requests.FuncionarioPatch;
import com.fiap.N.I.B.gateways.responses.FuncionarioPostResponse;
import com.fiap.N.I.B.usecases.Funcionario.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Operation(summary = "Busca um funcionário por ID", description = "Retorna um usuário com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrada"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrada")
    })
    @GetMapping("/registroFuncionario/{idFuncionario}")
    public ResponseEntity<EntityModel<Funcionario>> buscarPorRegistro(@PathVariable String idFuncionario) {
        Optional<Funcionario> profissional = funcionarioService.buscarFuncionario(idFuncionario);

        return profissional.map(funcionario -> {
            EntityModel<Funcionario> resource = EntityModel.of(funcionario);
            Link selfLink = linkTo(methodOn(FuncionarioController.class).buscarPorRegistro(String.valueOf(funcionario.getIdFuncionario()))).withSelfRel();
            Link allFuncsLink = linkTo(methodOn(FuncionarioController.class).buscarTodos()).withRel("all-funcs");

            resource.add(selfLink);

            resource.add(allFuncsLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário cadastrado"),
            @ApiResponse(responseCode = "404", description = "Funcionário não cadastrado"),
            @ApiResponse(responseCode = "409", description = "Funcionário já cadastrado em sistema")
    })
    @PostMapping("/criar")
    public ResponseEntity<EntityModel<FuncionarioPostResponse>> criarFuncionariol(@RequestBody Funcionario funcionarioParaCriar) {
        FuncionarioPostResponse respostaCriacao = funcionarioService.criarFuncionario(funcionarioParaCriar);

        EntityModel<FuncionarioPostResponse> resource = EntityModel.of(respostaCriacao);

        if (respostaCriacao.getMensagem().equals("Novo funcionario cadastrado")) {
            Link selfLink = linkTo(methodOn(FuncionarioController.class).buscarPorRegistro(String.valueOf(funcionarioParaCriar.getIdFuncionario()))).withSelfRel();
            Link allProfessionalsLink = linkTo(methodOn(FuncionarioController.class).buscarTodos()).withRel("all-funcs");

            resource.add(selfLink);
            resource.add(allProfessionalsLink);
            return ResponseEntity.status(201).body(resource);
        } else if (respostaCriacao.getMensagem().equals("E-mail já cadastrado em sistema")) {
            Link allProfessionalsLink = linkTo(methodOn(FuncionarioController.class).buscarTodos()).withRel("all-funcs");
            resource.add(allProfessionalsLink);
            return ResponseEntity.status(409).body(resource);

        } else {
            return ResponseEntity.status(404).body(resource);
        }
    }

    @Operation(summary = "Retorna todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionários listados"),
            @ApiResponse(responseCode = "404", description = "Funcionários não listados")
    })
    @GetMapping("/todos")
    public ResponseEntity<List<EntityModel<Funcionario>>> buscarTodos() {
        List<Funcionario> todosFuncionario = funcionarioService.buscarTodos();

        List<EntityModel<Funcionario>> todosFuncionariosComLink = todosFuncionario.stream().map(funcionario -> {
            EntityModel<Funcionario> resource = EntityModel.of(funcionario);
            Link selfLink = linkTo(methodOn(FuncionarioController.class).buscarPorRegistro(String.valueOf(funcionario.getIdFuncionario()))).withSelfRel();

            resource.add(selfLink);

            return resource;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(todosFuncionariosComLink);
    }

    @Operation(summary = "Atualiza totalmente o Funcionário", description = "Atualiza todas as informações do Funcionário buscando o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @PutMapping("/atualizar/{idFuncionario}")
    public ResponseEntity<EntityModel<Funcionario>> atualizarFuncionario(@PathVariable String idFuncionario,
                                                                          @RequestBody Funcionario funcionarioParaAtualizar) {
        Optional<Funcionario> funcionarioAtualizado = funcionarioService.atualizarFuncionario(idFuncionario, funcionarioParaAtualizar);

        return funcionarioAtualizado.map(funcionario -> {
            EntityModel<Funcionario> resource = EntityModel.of(funcionario);
            Link selfLink = linkTo(methodOn(FuncionarioController.class).buscarPorRegistro(String.valueOf(funcionario.getIdFuncionario()))).withSelfRel();
            Link allFuncsLink = linkTo(methodOn(FuncionarioController.class).buscarTodos()).withRel("all-funcs");

            resource.add(selfLink);

            resource.add(allFuncsLink);

            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza o email ou telefone do Funcionário", description = "Atualiza um funcionário com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrada"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrada")
    })
    @PatchMapping("/atualizar-email-telefone/{idFuncionario}")
    public ResponseEntity<EntityModel<Funcionario>> atualizarEmailTelefone(@PathVariable String idFuncionario,
                                                                           @RequestBody FuncionarioPatch funcionarioPatch) {
        Optional<Funcionario> profissionalAtualizado = funcionarioService.atualizarEmailTelefone(idFuncionario, funcionarioPatch);

        return profissionalAtualizado.map(funcionario -> {
            EntityModel<Funcionario> resource = EntityModel.of(funcionario);
            Link selfLink = linkTo(methodOn(FuncionarioController.class).buscarPorRegistro(String.valueOf(funcionario.getIdFuncionario()))).withSelfRel();
            Link allFuncsLink = linkTo(methodOn(FuncionarioController.class).buscarTodos()).withRel("all-funcs");

            resource.add(selfLink);

            resource.add(allFuncsLink);

            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um funcionário com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @DeleteMapping("/deletar/{idFuncionario}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable String idFuncionario) {
        boolean deletado = funcionarioService.deletarFuncionario(idFuncionario);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta um funcionário com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @DeleteMapping("/deletar/procedure/{idFuncionario}")
    public ResponseEntity<String> deletarFuncionarioProcedure(@PathVariable String idFuncionario) {
        boolean deletado = funcionarioService.deletarFuncionarioProcedure(Long.valueOf(idFuncionario));
        return deletado ? ResponseEntity.status(204).body("Funcionário dletado com sucesso") : ResponseEntity.notFound().build();
    }

}
