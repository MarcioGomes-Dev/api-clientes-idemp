package br.com.projetoidemp.api_clientes.controllers;

import br.com.projetoidemp.api_clientes.dtos.DashboardResponse;
import br.com.projetoidemp.api_clientes.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> dashboard() {

        return ResponseEntity.ok(
                dashboardService.obterIndicadores()
        );
    }
}