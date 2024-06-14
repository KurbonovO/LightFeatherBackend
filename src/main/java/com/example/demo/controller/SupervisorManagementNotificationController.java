package com.example.demo.controller;

import com.example.demo.request.Supervisor;
import com.example.demo.service.SupervisorManagementNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Supervisor Management", description = "Operations related to supervisors")
public class SupervisorManagementNotificationController {

  private final SupervisorManagementNotificationService supervisorManagementNotificationService;

  @Autowired
  public SupervisorManagementNotificationController(
      SupervisorManagementNotificationService supervisorManagementNotificationService) {
    this.supervisorManagementNotificationService = supervisorManagementNotificationService;
  }

  @GetMapping(path = "/supervisors", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Get list of supervisors",
      description = "Returns a list of supervisor names")
  public List<String> getSupervisors() {

    return supervisorManagementNotificationService.getSupervisor();
  }

  @PostMapping(path = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Submit supervisor details",
      description = "Submits details of a new supervisor")
  public ResponseEntity<?> postSupervisor(@Valid @RequestBody final Supervisor supervisor) {

    System.out.println(supervisor.toString());
    return ResponseEntity.ok("Supervisor submitted successfully");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    errors.put("message", "Validation failed for the request");
    return ResponseEntity.badRequest().body(errors);
  }
}
