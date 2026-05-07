package br.com.projetoidemp.api_clientes.repositories;

import br.com.projetoidemp.api_clientes.entities.Cliente;
import br.com.projetoidemp.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void inserir(Cliente cliente) throws Exception {

        try (var connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            var statement = connection.prepareStatement("INSERT INTO  " + "CLIENTES(NOME, EMAIL, TELEFONE) VALUES(?,?,?)");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getTelefone());
            statement.execute();

            var generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                cliente.setId(generatedKeys.getInt(1));
            }
            connection.commit();
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

    public List<Cliente> listar(String nome) throws Exception {
        try (var connection = ConnectionFactory.getConnection()){
            var statement = connection.prepareStatement("SELECT * FROM CLIENTES WHERE NOME ILIKE ? ORDER BY NOME");
            statement.setString(1,"%" + nome + "%");
            var result = statement.executeQuery();

            var lista = new ArrayList<Cliente>();

            while(result.next()){
                var cliente = new Cliente();

                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setEmail(result.getString("email"));
                cliente.setTelefone(result.getString("telefone"));

                lista.add(cliente);
            }

            return lista;
        }
    }
}