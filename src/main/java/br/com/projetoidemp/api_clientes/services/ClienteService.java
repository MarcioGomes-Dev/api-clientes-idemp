package br.com.projetoidemp.api_clientes.services;

import br.com.projetoidemp.api_clientes.dtos.ClienteRequest;
import br.com.projetoidemp.api_clientes.dtos.ClienteResponse;
import br.com.projetoidemp.api_clientes.dtos.ClienteUpdateRequest;
import br.com.projetoidemp.api_clientes.entities.Cliente;
import br.com.projetoidemp.api_clientes.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // =========================================
    // CADASTRAR CLIENTE
    // =========================================
    public void cadastrarCliente(ClienteRequest request) {

        if (request.nome() == null ||
                request.nome().trim().length() < 6) {

            throw new IllegalArgumentException(
                    "O nome do cliente é obrigatório e deve ter pelo menos 6 caracteres."
            );
        }

        if (request.email() == null ||
                request.email().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O Email do cliente é obrigatório!"
            );
        }

        if(clienteRepository.existsByEmail(request.email())) {

            throw new IllegalArgumentException(
                    "O Email já está cadastrado. Tente outro."
            );
        }

        var cliente = new Cliente();

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        clienteRepository.save(cliente);
    }

    // =========================================
    // PESQUISAR CLIENTES
    // =========================================
    public List<ClienteResponse> pesquisarClientes(String nome) {

        if(nome == null || nome.trim().length() < 5) {

            throw new IllegalArgumentException(
                    "O nome do cliente para pesquisa deve ter pelo menos 5 caracteres."
            );
        }

        var lista = clienteRepository
                .findByNomeContainingIgnoreCaseOrderByNome(nome);

        return lista.stream()
                .map(cliente -> new ClienteResponse(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getTelefone()
                ))
                .toList();
    }

    // =========================================
    // ATUALIZAR CLIENTE
    // =========================================
    public void atualizarCliente(
            Integer id,
            ClienteUpdateRequest request) {

        var cliente = clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Cliente não encontrado."
                        )
                );

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        clienteRepository.save(cliente);
    }

    // =========================================
    // EXCLUIR CLIENTE
    // =========================================
    public void excluirCliente(Integer id) {

        if(!clienteRepository.existsById(id)) {

            throw new IllegalArgumentException(
                    "Cliente não encontrado."
            );
        }

        clienteRepository.deleteById(id);
    }

    // =========================================
    // OBTER CLIENTE POR ID
    // =========================================
    public ClienteResponse obterClientePorId(Integer id) {

        var cliente = clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Cliente não encontrado."
                        )
                );

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}