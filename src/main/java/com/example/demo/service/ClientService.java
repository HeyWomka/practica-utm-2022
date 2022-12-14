package com.example.demo.service;

import com.example.demo.model.Client;

import java.util.List;

public interface ClientService {

  /*
   * Create a new client
   */
  Client create(Client client);

  /*
   * Get a client by id
   */
  Client findById(String id);

  /*
   * Get all clients
   */
  List<Client> readAll();

}
