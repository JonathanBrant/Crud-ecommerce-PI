package com.raphael.crud.services;

import com.raphael.crud.domain.Cliente;
import com.raphael.crud.dto.ClienteDTO;
import com.raphael.crud.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Long id) {
        Optional <Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow();
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setName(clienteDTO.getName());
        cliente.setPhone(clienteDTO.getPhone());
        return cliente;
    }

    public Cliente insert(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        Cliente updatedCliente = buscar(cliente.getId());
        updateData(updatedCliente, cliente);
        return clienteRepository.save(updatedCliente);
    }

    private void updateData(Cliente updatedCliente, Cliente cliente) {
        updatedCliente.setName(cliente.getName());
        updatedCliente.setPhone(cliente.getPhone());
    }

    public void delete(Long id) {
        buscar(id);
        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }
}
