package com.raphael.crud.controller;

import com.raphael.crud.domain.Cliente;
import com.raphael.crud.dto.ClienteDTO;
import com.raphael.crud.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value="/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){
        Cliente cliente = clienteServices.buscar(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteServices.fromDTO(clienteDTO);
        cliente = clienteServices.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity <Void> update (@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        Cliente cliente = clienteServices.fromDTO(clienteDTO);
        cliente.setId(id);
        cliente = clienteServices.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
        public ResponseEntity <Void> delete (@PathVariable Long id){
        clienteServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<ClienteDTO>> findAll(){
        List<Cliente> list = clienteServices.buscarTodos();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }




}
