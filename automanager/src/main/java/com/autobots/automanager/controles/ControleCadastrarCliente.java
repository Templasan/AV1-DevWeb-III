package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dtos.ClienteCadastrarDTO;
import com.autobots.automanager.servicos.ClienteServico;

@RestController
@RequestMapping("/cliente")
public class ControleCadastrarCliente {

    @Autowired
    private ClienteServico servico;

    @PostMapping("/cadastro")
    public void cadastrarCliente(@RequestBody ClienteCadastrarDTO dto) {
        servico.cadastrarViaDTO(dto);
    }
}