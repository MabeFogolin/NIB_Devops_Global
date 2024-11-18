package com.fiap.N.I.B.gateways.Endereco;

import com.fiap.N.I.B.domains.Endereco;
import com.fiap.N.I.B.gateways.requests.EnderecoPatch;
import com.fiap.N.I.B.gateways.responses.EnderecoPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoServiceImpl enderecoService;

    @Operation(summary = "Cria um novo Endereço", description = "Endpoint acessível para criação de endereços, atribuindo ou a usuário ou funcionário de acordo com o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para criação do endereço")
    })
    @PostMapping("/criar/{idPessoa}")
    public ResponseEntity<EntityModel<EnderecoPostResponse>> criarEndereco(@PathVariable String idPessoa, @RequestBody Endereco endereco) {
        EnderecoPostResponse response = enderecoService.criarEndereco(idPessoa, endereco);

        EntityModel<EnderecoPostResponse> resource = EntityModel.of(response);
        Link selfLink = linkTo(methodOn(EnderecoController.class).buscarEnderecoPorFuncionario(idPessoa)).withSelfRel();
        Link allEnderecosLink = linkTo(methodOn(EnderecoController.class).listarTodos()).withRel("all-enderecos");
        resource.add(selfLink);
        resource.add(allEnderecosLink);

        if (response.getEndereco() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resource);
        }
    }

    @Operation(summary = "Atualiza parcialmente um endereço", description = "Endpoint acessível para a atualização de endereços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para atualizaçaõ do endereço")
    })
    @PatchMapping("/{idPessoa}")
    public ResponseEntity<EntityModel<Endereco>> atualizarParcial(@PathVariable String idPessoa, @RequestBody EnderecoPatch enderecoPatch) {
        Optional<Endereco> enderecoAtualizado = enderecoService.atualizarParcial(idPessoa, enderecoPatch);

        return enderecoAtualizado.map(endereco -> {
            EntityModel<Endereco> resource = EntityModel.of(endereco);
            Link selfLink = linkTo(methodOn(EnderecoController.class).buscarEnderecoPorUsuario(idPessoa)).withSelfRel();
            Link allEnderecosLink = linkTo(methodOn(EnderecoController.class).listarTodos()).withRel("all-enderecos");
            resource.add(selfLink);
            resource.add(allEnderecosLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Atualiza totalmente um endereço", description = "Endpoint acessível para a atualização de endereços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para atualização do endereço")
    })
    @PutMapping("/atualizar/{idPessoa}")
    public ResponseEntity<EntityModel<Endereco>> atualizarTotalmente(@PathVariable String idPessoa, @RequestBody Endereco enderecoParaAtualizar) {
        Optional<Endereco> enderecoAtualizado = enderecoService.atualizarTotalmente(idPessoa, enderecoParaAtualizar);

        return enderecoAtualizado.map(endereco -> {
            EntityModel<Endereco> resource = EntityModel.of(endereco);
            Link selfLink = linkTo(methodOn(EnderecoController.class).buscarEnderecoPorUsuario(idPessoa)).withSelfRel();
            Link allEnderecosLink = linkTo(methodOn(EnderecoController.class).listarTodos()).withRel("all-enderecos");
            resource.add(selfLink);
            resource.add(allEnderecosLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Deleta um endereço", description = "Endpoint acessível para deleta endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para deletar")
    })
    @DeleteMapping("/deletar/{idPessoa}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable String idPessoa) {
        boolean deletado = enderecoService.deletarEndereco(idPessoa);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Recupera o endereço do funcionário com parte do ID", description = "Endpoint acessível para recuperar o endereço do funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para encontrar o endereço")
    })
    @GetMapping("/funcionario/{idFuncionario}")
    public ResponseEntity<EntityModel<Endereco>> buscarEnderecoPorFuncionario(@PathVariable String idFuncionario) {
        Optional<Endereco> endereco = enderecoService.buscarEnderecoPorFuncionario(idFuncionario);

        return endereco.map(e -> {
            EntityModel<Endereco> resource = EntityModel.of(e);
            Link selfLink = linkTo(methodOn(EnderecoController.class).buscarEnderecoPorFuncionario(idFuncionario)).withSelfRel();
            Link allEnderecosLink = linkTo(methodOn(EnderecoController.class).listarTodos()).withRel("all-enderecos");
            resource.add(selfLink);
            resource.add(allEnderecosLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Recupera o endereço do Usuário com parte do ID", description = "Endpoint acessível para recuperar o endereço do funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para encontrar o endereço")
    })
    @GetMapping("/usuario/{cpfUser}")
    public ResponseEntity<EntityModel<Endereco>> buscarEnderecoPorUsuario(@PathVariable String cpfUser) {
        Optional<Endereco> endereco = enderecoService.buscarEnderecoPorUsuario(cpfUser);

        return endereco.map(e -> {
            EntityModel<Endereco> resource = EntityModel.of(e);
            Link selfLink = linkTo(methodOn(EnderecoController.class).buscarEnderecoPorUsuario(cpfUser)).withSelfRel();
            Link allEnderecosLink = linkTo(methodOn(EnderecoController.class).listarTodos()).withRel("all-enderecos");
            resource.add(selfLink);
            resource.add(allEnderecosLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Recupera todos os endereços", description = "Endpoint acessível para recuperar todos os endereços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços listados com sucesso"),
    })
    @GetMapping("/todos")
    public ResponseEntity<List<EntityModel<Endereco>>> listarTodos() {
        List<Endereco> enderecos = enderecoService.listarTodos();

        List<EntityModel<Endereco>> enderecosComLink = enderecos.stream().map(endereco -> {
            EntityModel<Endereco> resource = EntityModel.of(endereco);

            if (endereco.getUsuarioGlobal() != null && endereco.getUsuarioGlobal().getCpfUser() != null) {
                Link selfLink = linkTo(methodOn(EnderecoController.class)
                        .buscarEnderecoPorUsuario(endereco.getUsuarioGlobal().getCpfUser())).withSelfRel();
                resource.add(selfLink);
            }

            if (endereco.getFuncionario() != null && endereco.getFuncionario().getIdFuncionario() != null) {
                Link selfLinkProfissional = linkTo(methodOn(EnderecoController.class)
                        .buscarEnderecoPorFuncionario(String.valueOf(endereco.getFuncionario().getIdFuncionario()))).withSelfRel();
                resource.add(selfLinkProfissional);
            }
            return resource;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(enderecosComLink);
    }
}
