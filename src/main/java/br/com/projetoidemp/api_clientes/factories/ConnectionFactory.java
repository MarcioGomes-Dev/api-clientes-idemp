package br.com.projetoidemp.api_clientes.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConnection() throws Exception{

        var host = "jdbc:postgresql://localhost:5432/bd-api-clientes-idemp";
        var user = "postgres";
        var pass = "2708";

        return DriverManager.getConnection(host, user, pass);
    }
}
