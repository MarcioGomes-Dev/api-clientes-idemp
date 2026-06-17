package br.com.projetoidemp.api_clientes.services;

import br.com.projetoidemp.api_clientes.dtos.ClienteRequest;
import br.com.projetoidemp.api_clientes.dtos.ClienteResponse;
import br.com.projetoidemp.api_clientes.dtos.ClienteUpdateRequest;
import br.com.projetoidemp.api_clientes.dtos.DashboardResponse;
import br.com.projetoidemp.api_clientes.entities.Cliente;
import br.com.projetoidemp.api_clientes.enums.StatusRelatorio;
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

        if (clienteRepository.existsByEmail(request.email())) {

            throw new IllegalArgumentException(
                    "O Email já está cadastrado. Tente outro."
            );
        }

        var cliente = new Cliente();

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        cliente.setStatusRelatorio(
                StatusRelatorio.NAO_RESPONDIDO
        );

        clienteRepository.save(cliente);
    }

    // =========================================
    // PESQUISAR CLIENTES
    // =========================================
    public List<ClienteResponse> pesquisarClientes(String nome) {

        if (nome == null || nome.trim().length() < 3) {

            throw new IllegalArgumentException(
                    "Informe pelo menos 3 caracteres para pesquisa."
            );
        }

        var lista = clienteRepository
                .findByNomeContainingIgnoreCaseOrderByNome(nome);

        return lista.stream()
                .map(cliente -> new ClienteResponse(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getTelefone(),
                        cliente.getStatusRelatorio() != null
                                ? cliente.getStatusRelatorio().name()
                                : "NAO_RESPONDIDO"
                ))
                .toList();
    }

    // =========================================
    // LISTAR TODOS OS CLIENTES
    // =========================================
    public List<ClienteResponse> listarTodosClientes() {

        var lista = clienteRepository.findAll();

        return lista.stream()
                .map(cliente -> new ClienteResponse(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getTelefone(),
                        cliente.getStatusRelatorio() != null
                                ? cliente.getStatusRelatorio().name()
                                : "NAO_RESPONDIDO"
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

        if (request.statusRelatorio() != null) {

            cliente.setStatusRelatorio(
                    StatusRelatorio.valueOf(
                            request.statusRelatorio()
                    )
            );
        }

        clienteRepository.save(cliente);
    }

    // =========================================
    // EXCLUIR CLIENTE
    // =========================================
    public void excluirCliente(Integer id) {

        if (!clienteRepository.existsById(id)) {

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
                cliente.getTelefone(),
                cliente.getStatusRelatorio() != null
                        ? cliente.getStatusRelatorio().name()
                        : "NAO_RESPONDIDO"
        );
    }

    // =========================================
    // ALTERAR STATUS
    // =========================================
    public void alterarStatus(
            Integer id,
            String status) {

        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Cliente não encontrado."
                        )
                );

        cliente.setStatusRelatorio(
                StatusRelatorio.valueOf(status)
        );

        clienteRepository.save(cliente);
    }
    public DashboardResponse obterIndicadores() {

        Long totalClientes =
                clienteRepository.count();

        Long respondidos =
                clienteRepository
                        .countByStatusRelatorio(
                                StatusRelatorio.RESPONDIDO
                        );

        Long pendentes =
                clienteRepository
                        .countByStatusRelatorio(
                                StatusRelatorio.NAO_RESPONDIDO
                        );

        return new DashboardResponse(
                totalClientes,
                respondidos,
                pendentes
        );
    }

}