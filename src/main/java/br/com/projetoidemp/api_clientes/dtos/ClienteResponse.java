package br.com.projetoidemp.api_clientes.dtos;

import br.com.projetoidemp.api_clientes.enums.StatusRelatorio;

public record ClienteResponse(

        Integer id,
        String nome,
        String email,
        String telefone,
        StatusRelatorio statusRelatorio

) {}
