package br.com.projetoidemp.api_clientes.dtos;

public record ClienteRequest(
        String nome,
        String email,
        String telefone
) {
}
