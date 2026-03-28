package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dtos.ClienteExibirDTO;
import com.autobots.automanager.servicos.ClienteServico;

@RestController
@RequestMapping("/cliente")
public class ControleObterClienteId {

    @Autowired
    private ClienteServico servico;

    @GetMapping("/{id}")
	public ClienteExibirDTO obterCliente(@PathVariable long id) {
    	return servico.buscarPorIdDTO(id);
	}
	
}