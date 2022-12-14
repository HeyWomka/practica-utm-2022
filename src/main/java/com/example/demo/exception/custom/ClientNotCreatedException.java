package com.example.demo.exception.custom;

public class ClientNotCreatedException extends Exception {
  public ClientNotCreatedException() {
    super("Client not created");
  }
}
