package com.autobots.automanager.dtos;

import java.util.Date;
import java.util.List;
import lombok.Data;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;

@Data
public class ClienteExibirDTO {
    private Long id;
    private String nome;
    private String nomeSocial;
    private Date dataNascimento;
    private Date dataCadastro;

    private Endereco endereco;
    private List<Telefone> telefones;
    private List<Documento> documentos;
}