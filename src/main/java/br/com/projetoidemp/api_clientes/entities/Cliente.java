package br.com.projetoidemp.api_clientes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import br.com.projetoidemp.api_clientes.enums.StatusRelatorio;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Enumerated(EnumType.STRING)
    private StatusRelatorio statusRelatorio;

    // getters e setters
}