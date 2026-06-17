package br.com.projetoidemp.api_clientes.repositories;

import br.com.projetoidemp.api_clientes.entities.Cliente;

import br.com.projetoidemp.api_clientes.enums.StatusRelatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente, Integer> {

    // =========================================
    // VERIFICAR EMAIL EXISTENTE
    // =========================================
    boolean existsByEmail(String email);

    // =========================================
    // LISTAR CLIENTES POR NOME
    // =========================================
    List<Cliente> findByNomeContainingIgnoreCaseOrderByNome(String nome);

    //==========================================
    //TOTAL DE CLIENTES
    //==========================================
    Long countByStatusRelatorio(
            StatusRelatorio statusRelatorio
    );
}