package br.com.projetoidemp.api_clientes.dtos;

public record ClienteResponse(

        Integer id,
        String nome,
        String email,
        String telefone,
        String statusRelatorio

) {}
