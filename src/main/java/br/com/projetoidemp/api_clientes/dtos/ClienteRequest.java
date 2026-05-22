package br.com.projetoidemp.api_clientes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 6, message = "O nome deve ter pelo menos 6 caracteres.")
        String nome,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Informe um email válido.")
        String email,

        String telefone
) {
}
