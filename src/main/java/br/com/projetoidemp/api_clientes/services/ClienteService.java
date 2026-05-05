package br.com.projetoidemp.api_clientes.services;

import br.com.projetoidemp.api_clientes.entities.Cliente;
import br.com.projetoidemp.api_clientes.repositories.ClienteRepository;

public class ClienteService {

    public void cadastrarCliente(String nome, String email, String telefone) throws Exception {

        //Verificar se o nome está preenchido com pelo  //menos 6 caracteres
        if (nome == null || nome.trim().length() < 6) {
            throw new IllegalArgumentException("O nome do cliente  é obrigatório e deve ter pelo menos 6 caracteres.");

        }

        if(email == null){
            throw new IllegalArgumentException("O Email do cliente é obrigatório!");
        }
        var clienteRepository = new ClienteRepository();
        if(clienteRepository.emailExistente(email)){
            throw new IllegalArgumentException("O Email já está cadastrado. Tente outro.");
        }
        var cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);//Salvando o cliente no banco de dados

        clienteRepository.inserir(cliente);
    }
}
