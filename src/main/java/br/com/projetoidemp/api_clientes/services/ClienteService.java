package br.com.projetoidemp.api_clientes.services;

import br.com.projetoidemp.api_clientes.dtos.ClienteRequest;
import br.com.projetoidemp.api_clientes.entities.Cliente;
import br.com.projetoidemp.api_clientes.repositories.ClienteRepository;

import java.util.List;

public class ClienteService {

    public void cadastrarCliente(ClienteRequest request) throws Exception {

        //Verificar se o nome está preenchido com pelo  //menos 6 caracteres
        if (request.nome() == null || request.nome().trim().length() < 6) {
            throw new IllegalArgumentException("O nome do cliente  é obrigatório e deve ter pelo menos 6 caracteres.");
        }

        if(request.email() == null){
            throw new IllegalArgumentException("O Email do cliente é obrigatório!");
        }
        var clienteRepository = new ClienteRepository();
        if(clienteRepository.emailExistente(request.email())){
            throw new IllegalArgumentException("O Email já está cadastrado. Tente outro.");
        }
        var cliente = new Cliente();
        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());//Salvando o cliente no banco de dados

        clienteRepository.inserir(cliente);
    }

    public List<Cliente> pesquisarClientes (String nome) throws Exception {

        if(nome == null || nome.trim().length() < 5) {
            throw new IllegalArgumentException("O nome do cliente para pesquisa deve ter pelo menos 5 caracteres.");
        }
        var clienteRepository = new ClienteRepository();
        var lista = clienteRepository.listar(nome);

        return lista;
    }
}
