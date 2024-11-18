package com.fiap.N.I.B.gateways.Tomada;

import com.fiap.N.I.B.domains.Tomada;
import com.fiap.N.I.B.gateways.requests.TomadaPatch;
import com.fiap.N.I.B.gateways.responses.TomadaPostResponse;
import com.fiap.N.I.B.usecases.Tomada.TomadaService;
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
@RequestMapping("/tomada")
@RequiredArgsConstructor
public class TomadaController {

    private final TomadaService tomadaService;

    @Operation(summary = "Busca uma tomada por ID", description = "Retorna uma tomada com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tomada encontrada"),
            @ApiResponse(responseCode = "404", description = "Tomada não encontrada")
    })
    @GetMapping("/{idTomada}")
    public ResponseEntity<EntityModel<Tomada>> buscarPorId(@PathVariable int idTomada) {
        Optional<Tomada> tomada = tomadaService.buscarTomadaPorId(idTomada);

        return tomada.map(t -> {
            EntityModel<Tomada> resource = EntityModel.of(t);
            Link selfLink = linkTo(methodOn(TomadaController.class).buscarPorId(t.getIdTomada())).withSelfRel();
            Link allTomadasLink = linkTo(methodOn(TomadaController.class).buscarTodasTomadas()).withRel("all-tomadas");

            resource.add(selfLink);
            resource.add(allTomadasLink);

            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova tomada", description = "É necessário ter o usuário cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tomada cadastrada"),
            @ApiResponse(responseCode = "404", description = "Tomada não cadastrada")
    })
    @PostMapping("/criar")
    public ResponseEntity<EntityModel<TomadaPostResponse>> criarTomada(@RequestBody Tomada tomadaParaCriar, @RequestParam String cpfUser) {
        TomadaPostResponse respostaCriacao = tomadaService.criarTomada(cpfUser, tomadaParaCriar);
        EntityModel<TomadaPostResponse> resource = EntityModel.of(respostaCriacao);
        if ("Tomada cadastrada com sucesso".equals(respostaCriacao.getMensagem())) {
            Link selfLink = linkTo(methodOn(TomadaController.class).buscarPorId(tomadaParaCriar.getIdTomada())).withSelfRel();
            Link allTomadasLink = linkTo(methodOn(TomadaController.class).buscarTodasTomadas()).withRel("all-tomadas");

            resource.add(selfLink);
            resource.add(allTomadasLink);

            return ResponseEntity.status(201).body(resource);
        }
        else if ("Tomada não cadastrada, Usuário não encontrado".equals(respostaCriacao.getMensagem())) {
            return ResponseEntity.status(404).body(resource);
        }

        else {
            return ResponseEntity.status(409).body(resource);
        }
    }

    @Operation(summary = "Retorna todas as tomadas com base no usuário informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tomadas listadas"),
            @ApiResponse(responseCode = "404", description = "Tomadas não listadas")
    })
    @GetMapping("/todas/usuario")
    public ResponseEntity<List<EntityModel<Tomada>>> tomadasPorUsuario(@RequestParam String cpfUser) {
        List<Tomada> todasTomadas = tomadaService.buscarTodasPorUsuario(cpfUser);

        List<EntityModel<Tomada>> todasTomadasComLink = todasTomadas.stream().map(tomada -> {
            EntityModel<Tomada> resource = EntityModel.of(tomada);
            Link selfLink = linkTo(methodOn(TomadaController.class).buscarPorId(tomada.getIdTomada())).withSelfRel();

            resource.add(selfLink);
            return resource;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(todasTomadasComLink);
    }


    @Operation(summary = "Retorna todas as tomadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tomada listadas"),
            @ApiResponse(responseCode = "404", description = "Tomada não listadas")
    })
    @GetMapping("/todas")
    public ResponseEntity<List<EntityModel<Tomada>>> buscarTodasTomadas() {
        List<Tomada> todasTomadas = tomadaService.buscarTodasTomadas();

        List<EntityModel<Tomada>> todasTomadasComLink = todasTomadas.stream().map(tomada -> {
            EntityModel<Tomada> resource = EntityModel.of(tomada);
            Link selfLink = linkTo(methodOn(TomadaController.class).buscarPorId(tomada.getIdTomada())).withSelfRel();

            resource.add(selfLink);
            return resource;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(todasTomadasComLink);
    }

    @Operation(summary = "Atualiza todas as informações de uma tomada", description = "Busca uma tomada já cadastrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tomada atualizada"),
            @ApiResponse(responseCode = "404", description = "Tomada não encontrada")
    })
    @PutMapping("/atualizar/{idTomada}")
    public ResponseEntity<EntityModel<Tomada>> atualizarTomada(@PathVariable int idTomada, @RequestBody Tomada tomadaParaAtualizar) {
        Optional<Tomada> tomadaAtualizada = tomadaService.atualizarTomada(idTomada, tomadaParaAtualizar);

        return tomadaAtualizada.map(tomada -> {
            EntityModel<Tomada> resource = EntityModel.of(tomada);
            Link selfLink = linkTo(methodOn(TomadaController.class).buscarPorId(tomada.getIdTomada())).withSelfRel();
            Link allTomadasLink = linkTo(methodOn(TomadaController.class).buscarTodasTomadas()).withRel("all-tomadas");

            resource.add(selfLink);
            resource.add(allTomadasLink);

            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta uma tomada com base no ID", description = "Busca uma tomada já cadastrada para deletar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tomada não deletada"),
            @ApiResponse(responseCode = "404", description = "Tomada não encontrada")
    })
    @DeleteMapping("/deletar/{idTomada}")
    public ResponseEntity<Void> deletarTomada(@PathVariable int idTomada) {
        boolean deletado = tomadaService.removerTomada(idTomada);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza o nome da tomada", description = "Busca uma tomada já cadastrada para atualizar o nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tomada atualizada"),
            @ApiResponse(responseCode = "404", description = "Tomada não encontrada")
    })
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<EntityModel<Tomada>> atualizarNomeTomada(@PathVariable int id, @RequestBody TomadaPatch tomadaPatch) {
        Optional<Tomada> tomadaAtualizada = tomadaService.atualizarInfo(id, tomadaPatch);

        return tomadaAtualizada.map(t -> {
            EntityModel<Tomada> resource = EntityModel.of(t);
            Link selfLink = linkTo(methodOn(TomadaController.class).buscarPorId(t.getIdTomada())).withSelfRel();
            resource.add(selfLink);
            return ResponseEntity.ok(resource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
