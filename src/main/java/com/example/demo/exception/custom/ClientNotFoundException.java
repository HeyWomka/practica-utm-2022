package com.example.demo.exception.custom;

public class ClientNotFoundException extends Exception {
  public ClientNotFoundException(String id) {
    super(String.format("Client with id = %s not found", id));
  }
}
