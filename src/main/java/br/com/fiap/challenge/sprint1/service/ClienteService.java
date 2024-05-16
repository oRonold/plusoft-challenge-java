package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;
import br.com.fiap.challenge.sprint1.model.endereco.estado.Estado;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.Logradouro;
import br.com.fiap.challenge.sprint1.model.endereco.pais.Pais;
import br.com.fiap.challenge.sprint1.model.ramo.Ramo;
import br.com.fiap.challenge.sprint1.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(CadastrarClienteDTO dto){
        var cliente = new Cliente(dto);
        var ramo = new Ramo(dto);
        cliente.setRamo(ramo);
        ramo.getCliente().add(cliente);

        var enderecoCliente = new EnderecoCliente(dto);
        var logradouro = new Logradouro(dto);
        var bairro = new Bairro(dto);
        var cidade = new Cidade(dto);
        var estado = new Estado(dto);
        var pais = new Pais(dto);

        enderecoCliente.setCliente(cliente);
        enderecoCliente.setLogradouro(logradouro);

        cliente.getEnderecoClientes().add(enderecoCliente);
        logradouro.getEnderecoClientes().add(enderecoCliente);

        logradouro.setBairro(bairro);
        bairro.getLogradouros().add(logradouro);

        bairro.setCidade(cidade);
        cidade.getBairros().add(bairro);

        cidade.setEstado(estado);
        estado.getCidades().add(cidade);

        estado.setPais(pais);
        pais.getEstados().add(estado);

        clienteRepository.save(cliente);
        return cliente;
    }

}
