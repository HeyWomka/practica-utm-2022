package com.example.demo.controller;

import com.example.demo.exception.model.ErrorMessage;
import com.example.demo.model.Client;
import com.example.demo.model.dto.ClientDto;
import com.example.demo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("ping")
  public ResponseEntity<String> ping() {
    return new ResponseEntity<>("pong", HttpStatus.OK);
  }

  @PostMapping("clients")
  public ResponseEntity<Client> create(@RequestBody @Valid ClientDto clientDto) {
    Client savedClient = clientService.create(clientDto.toClient());
    return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
  }

  @GetMapping("clients/{id}")
  public ResponseEntity<Client> read(@PathVariable(name = "id") String id) {
    final Client client = clientService.findById(id);
    return ObjectUtils.isEmpty(client)
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(client, HttpStatus.OK);
  }

  @GetMapping("clients")
  public ResponseEntity<Object> readAll() {
    final List<Client> clients = clientService.readAll();
    return ObjectUtils.isEmpty(clients)
        ? new ResponseEntity<>(new ErrorMessage("Clients not found"), HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(clients, HttpStatus.OK);
  }
}
