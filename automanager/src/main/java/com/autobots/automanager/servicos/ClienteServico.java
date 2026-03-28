package com.autobots.automanager.servicos;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dtos.ClienteAtualizarDTO;
import com.autobots.automanager.dtos.ClienteCadastrarDTO;
import com.autobots.automanager.dtos.ClienteExibirDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.excecoes.personalizado.EntidadeNaoEncontradaException;
import com.autobots.automanager.modelo.ClienteAtualizador;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio repositorio;

    @Autowired
    private ClienteSelecionador selecionador;

    public List<ClienteExibirDTO> buscarTodos() {
        List<Cliente> clientes = repositorio.findAll();
        return clientes.stream()
                .map(this::converterParaExibirDTO)
                .collect(Collectors.toList());
    }

    public ClienteExibirDTO buscarPorIdDTO(Long id) {
        List<Cliente> clientes = repositorio.findAll();
    
        Cliente cliente = selecionador.selecionar(clientes, id);
        if (cliente == null) {
            throw new EntidadeNaoEncontradaException(
                "Cliente não encontrado", 
                "Não foi possível localizar um cliente com o ID: " + id
            );
        }
        
        return converterParaExibirDTO(cliente);
        
    }

    public void cadastrar(Cliente cliente) {
        repositorio.save(cliente);
    }

    public void atualizar(Cliente atualizacao) {
        Cliente cliente = repositorio.findById(atualizacao.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    "Atualização impossível", 
                    "O cliente de ID " + atualizacao.getId() + " não foi encontrado no banco."));
        
        ClienteAtualizador atualizador = new ClienteAtualizador();
        atualizador.atualizar(cliente, atualizacao);
        repositorio.save(cliente);
    }

    public void excluir(Long id) {
        Cliente cliente = repositorio.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    "Exclusão negada", 
                    "Não foi possível localizar o cliente de ID " + id + " para remover."));
        repositorio.delete(cliente);
    }
    





    private ClienteExibirDTO converterParaExibirDTO(Cliente cliente) {
        ClienteExibirDTO dto = new ClienteExibirDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setNomeSocial(cliente.getNomeSocial());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setDataCadastro(cliente.getDataCadastro());
        dto.setEndereco(cliente.getEndereco());
        dto.setTelefones(cliente.getTelefones());
        dto.setDocumentos(cliente.getDocumentos());
        return dto;
    }

    public void cadastrarViaDTO(ClienteCadastrarDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setDataCadastro(new java.util.Date()); 
        cliente.setEndereco(dto.getEndereco());
        cliente.setTelefones(dto.getTelefones());
        cliente.setDocumentos(dto.getDocumentos());
        
        this.cadastrar(cliente);
    }

    public void atualizarViaDTO(ClienteAtualizarDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setEndereco(dto.getEndereco());
        cliente.setTelefones(dto.getTelefones());
        cliente.setDocumentos(dto.getDocumentos());
        
        this.atualizar(cliente); 
    }
}