package br.com.projetoidemp.api_clientes.factories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class ConnectionFactory {

    @Value("${spring.datasource.url}")
    private String host;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pass;

    public Connection getConnection() throws Exception{

        return DriverManager.getConnection(host, user, pass);
    }
}
