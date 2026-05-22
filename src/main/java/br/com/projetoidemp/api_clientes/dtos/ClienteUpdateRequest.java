package br.com.projetoidemp.api_clientes.dtos;

public record ClienteUpdateRequest(

        String nome,
        String email,
        String telefone

) {
}