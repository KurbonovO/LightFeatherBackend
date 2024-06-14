package com.example.demo.service;

import com.example.demo.feign.AwsManagers;
import com.example.demo.response.SupervisorManagementNotificationResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisorManagementNotificationService {

  private static final String SPACE_DASH_SPACE = " - ";
  private final AwsManagers awsManagers;

  @Autowired
  public SupervisorManagementNotificationService(AwsManagers awsManagers) {
    this.awsManagers = awsManagers;
  }

  public List<String> getSupervisor() {

    List<SupervisorManagementNotificationResponse> validResponses = getValidResponses();

    return validResponses.stream().map(this::formatSupervisorDetail).collect(Collectors.toList());
  }

  private List<SupervisorManagementNotificationResponse> getValidResponses() {
    return awsManagers.getAwsManagers().stream()
        .filter(this::isValidResponse)
        .sorted(
            Comparator.comparing(SupervisorManagementNotificationResponse::getJurisdiction)
                .thenComparing(SupervisorManagementNotificationResponse::getLastName)
                .thenComparing(SupervisorManagementNotificationResponse::getFirstName))
        .collect(Collectors.toList());
  }

  private boolean isValidResponse(SupervisorManagementNotificationResponse response) {
    return StringUtils.isNotBlank(response.getJurisdiction())
        && !NumberUtils.isCreatable(response.getJurisdiction())
        && StringUtils.isNotBlank(response.getLastName())
        && StringUtils.isNotBlank(response.getFirstName());
  }

  private String formatSupervisorDetail(SupervisorManagementNotificationResponse response) {
    return response.getJurisdiction()
        + SPACE_DASH_SPACE
        + response.getLastName()
        + SPACE_DASH_SPACE
        + response.getFirstName();
  }

}
