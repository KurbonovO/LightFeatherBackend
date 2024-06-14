package com.example.demo.feign;

import com.example.demo.response.SupervisorManagementNotificationResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-common", url = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com")
public interface AwsManagers {

    @RequestMapping(method = RequestMethod.GET, value = "/api/managers")
    List<SupervisorManagementNotificationResponse> getAwsManagers();

}
