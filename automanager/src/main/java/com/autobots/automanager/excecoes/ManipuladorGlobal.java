package com.autobots.automanager.excecoes;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.autobots.automanager.dtos.ErroRespostaDTO;
import com.autobots.automanager.excecoes.personalizado.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ManipuladorGlobal {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroRespostaDTO> manipularViolacaoIntegridade(DataIntegrityViolationException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
            "Erro de integridade de dados.", 
            "Provavelmente este registro (CPF/RG) já existe ou faltam dados obrigatórios."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroRespostaDTO> manipularNaoEncontrado(EntidadeNaoEncontradaException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(ex.getMessage(), "Recurso inexistente");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroRespostaDTO> manipularErroTipo(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
            "Tipo de dado inválido", 
            String.format("O parâmetro '%s' deveria ser do tipo %s", ex.getName(), ex.getRequiredType().getSimpleName())
        );
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErroRespostaDTO> manipularJsonInvalido(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO(
            "Erro na leitura do JSON", 
            "O corpo da requisição possui erros de sintaxe ou caracteres inválidos."
        );
        return ResponseEntity.badRequest().body(erro);
    }
}

