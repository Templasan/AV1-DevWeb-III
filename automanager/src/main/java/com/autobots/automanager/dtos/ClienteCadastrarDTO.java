package com.autobots.automanager.dtos;

import java.util.Date;
import java.util.List;
import lombok.Data;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;

@Data
public class ClienteCadastrarDTO {
    private String nome;
    private String nomeSocial;
    private Date dataNascimento;
    
    // Objetos relacionados que podem vir no cadastro inicial
    private Endereco endereco;
    private List<Telefone> telefones;
    private List<Documento> documentos;
}