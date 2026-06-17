package br.com.projetoidemp.api_clientes.services;

import br.com.projetoidemp.api_clientes.dtos.DashboardResponse;
import br.com.projetoidemp.api_clientes.enums.StatusRelatorio;
import br.com.projetoidemp.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private ClienteRepository clienteRepository;

    public DashboardResponse obterIndicadores() {

        Long totalClientes =
                clienteRepository.count();

        Long respondidos =
                clienteRepository.countByStatusRelatorio(
                        StatusRelatorio.RESPONDIDO
                );

        Long pendentes =
                clienteRepository.countByStatusRelatorio(
                        StatusRelatorio.NAO_RESPONDIDO
                );

        return new DashboardResponse(
                totalClientes,
                respondidos,
                pendentes
        );
    }
}
