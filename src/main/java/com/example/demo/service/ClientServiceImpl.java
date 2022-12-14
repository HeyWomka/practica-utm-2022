package com.example.demo.service;

import com.example.demo.exception.custom.ClientNotCreatedException;
import com.example.demo.exception.custom.ClientNotFoundException;
import com.example.demo.model.Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  private final MongoTemplate mongoTemplate;

  public ClientServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @SneakyThrows
  @Override
  public Client create(Client client) {
    try {
      Client savedClient = mongoTemplate.save(client);
      log.info(String.format("Client with id = %s has been saved", savedClient.getId()));
      return savedClient;
    } catch (Exception e) {
      throw new ClientNotCreatedException();
    }
  }

  @Override
  public List<Client> readAll() {
    return mongoTemplate.findAll(Client.class);
  }

  @SneakyThrows
  @Override
  public Client findById(String id) {
    return ensureThatClientExists(id);
  }

  private Client ensureThatClientExists(String id) throws ClientNotFoundException {
    Client client = mongoTemplate.findById(id, Client.class);

    if (ObjectUtils.isEmpty(client)) {
      throw new ClientNotFoundException(id);
    } else {
      return client;
    }
  }
}
