package com.example.demo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Supervisor {

  private static final String NAME_REQ = "^[A-Za-z]+$";

  @NotBlank(message = "First name is mandatory")
  @Pattern(regexp = NAME_REQ, message = "First name must contain only letters")
  @Schema(defaultValue = "John")
  private String firstName;

  @NotBlank(message = "Last name is mandatory")
  @Pattern(regexp = NAME_REQ, message = "Last name must contain only letters")
  @Schema(defaultValue = "Doe")
  private String lastName;

  @Email(message = "Email should be valid")
  @Schema(defaultValue = "JohnDoe@someemail.com")
  private String email;

  @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
  @Schema(example = "", defaultValue = "123-123-1234")
  private String phoneNumber;

  @NotBlank(message = "Supervisor is mandatory")
  @Schema(defaultValue = "Jane")
  private String supervisor;
  
}
