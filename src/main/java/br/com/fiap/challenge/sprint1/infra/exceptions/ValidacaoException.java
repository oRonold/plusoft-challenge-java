package br.com.fiap.challenge.sprint1.infra.exceptions;

public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String message) {
        super(message);
    }
}
