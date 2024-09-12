package br.com.fiap.challenge.sprint1.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> erro404(){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PesquisaIdNotFound.class)
    public ResponseEntity<PesquisaNotBelongsOrExist> erroIdPesquisa403(){
        return ResponseEntity.badRequest().body(new PesquisaNotBelongsOrExist("A pesquisa informada não existe ou não pertence a este usuario"));
    }

}
