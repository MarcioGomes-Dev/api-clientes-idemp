package br.com.projetoidemp.api_clientes.dtos;

public record DashboardResponse(
        Long totalClientes,
        Long clientesRespondidos,
        Long clientesPendentes
) {}
