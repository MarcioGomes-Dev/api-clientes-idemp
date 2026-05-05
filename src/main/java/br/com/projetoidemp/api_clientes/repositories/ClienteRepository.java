package br.com.projetoidemp.api_clientes.repositories;

import br.com.projetoidemp.api_clientes.entities.Cliente;
import br.com.projetoidemp.api_clientes.factories.ConnectionFactory;

public class ClienteRepository {
    public void inserir(Cliente cliente) throws Exception {

        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("INSERT INTO  " +
                    "CLIENTES(NOME, EMAIL, TELEFONE) VALUES(?,?,?)");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getTelefone());
            statement.execute();
        }
    }

    public boolean emailExistente(String email) throws Exception {
        try (var connection = ConnectionFactory.getConnection()) {
            var statement = connection.prepareStatement("SELECT COUNT(*) AS QTD FROM CLIENTES WHERE EMAIL = ?");
            statement.setString(1, email);
            var result = statement.executeQuery();
            if (result.next()) {
                return result.getInt("QTD") == 1;
            }
            return false;
        }
    }
}