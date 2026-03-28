package com.autobots.automanager.excecoes.personalizado;

import lombok.Getter;

@Getter
public class EntidadeNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String mensagem;

    public EntidadeNaoEncontradaException(String titulo, String mensagem) {
        super(titulo);
        this.mensagem = mensagem;
    }
}