package br.com.projetoidemp.api_clientes.exceptions;

public class EmailJaCadastradoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "O email informado já está cadastrado. Tente outro!";
    }
}

