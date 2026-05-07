package br.com.projetoidemp.api_clientes.entities;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonPropertyOrder({
        "id",
        "nome",
        "email",
        "telefone"
})
public class Cliente {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;

}
