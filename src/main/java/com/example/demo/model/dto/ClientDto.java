package com.example.demo.model.dto;

import com.example.demo.model.Client;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.*;

@Data
public class ClientDto {

  @NotEmpty(message = "The full name is required")
  @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters")
  private String name;

  @NotEmpty(message = "The email address is required")
  @Email(message = "The email address is invalid", flags = {Pattern.Flag.CASE_INSENSITIVE})
  private String email;

  private String description;

  public Client toClient() {
    return Client.builder()
        .name(name)
        .email(email)
        .description(ObjectUtils.isEmpty(description) ? "" : description)
        .build();
  }
}
