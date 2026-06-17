package br.com.projetoidemp.api_clientes.controllers;

import br.com.projetoidemp.api_clientes.dtos.ClienteRequest;
import br.com.projetoidemp.api_clientes.dtos.ClienteResponse;
import br.com.projetoidemp.api_clientes.dtos.ClienteUpdateRequest;
import br.com.projetoidemp.api_clientes.services.ClienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // =========================================
    // CADASTRAR CLIENTE
    // =========================================
    @PostMapping("/criar")
    public ResponseEntity<String> criar(
            @Valid @RequestBody ClienteRequest request) {

        clienteService.cadastrarCliente(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Cliente " + request.nome() + " cadastrado com sucesso!");
    }

    // =========================================
    // CONSULTAR CLIENTES
    // =========================================
    @GetMapping("/todos")
    public ResponseEntity<List<ClienteResponse>> listarTodos() {

        var lista = clienteService.listarTodosClientes();

        return ResponseEntity.ok(lista);
    }

    // =========================================
    // OBTER CLIENTE POR ID
    // =========================================
    @GetMapping("obterPorId/{id}")
    public ResponseEntity<ClienteResponse> obterPorId(
            @PathVariable Integer id) {

        var cliente = clienteService.obterClientePorId(id);

        return ResponseEntity.ok(cliente);
    }

    // =========================================
    // ATUALIZAR CLIENTE
    // =========================================
    @PutMapping("atualizar/{id}")
    public ResponseEntity<String> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteUpdateRequest request) {

        clienteService.atualizarCliente(id, request);

        return ResponseEntity.ok(
                "Cliente atualizado com sucesso!"
        );
    }

    // =========================================
    // EXCLUIR CLIENTE
    // =========================================
    @DeleteMapping("excluir/{id}")
    public ResponseEntity<String> excluir(
            @PathVariable Integer id) {

        clienteService.excluirCliente(id);

        return ResponseEntity.ok(
                "Cliente excluído com sucesso!"
        );
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> alterarStatus(
            @PathVariable Integer id,
            @RequestParam String status) {

        clienteService.alterarStatus(id, status);

        return ResponseEntity.ok(
                "Status atualizado com sucesso!"
        );
    }
    @GetMapping("/consultar")
    public ResponseEntity<List<ClienteResponse>> pesquisarPorNome(
            @RequestParam String nome) {

        var lista = clienteService.pesquisarClientes(nome);

        return ResponseEntity.ok(lista);
    }

}