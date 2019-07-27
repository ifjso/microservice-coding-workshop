package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.model.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationDiscoveryClient {

  @Autowired
  private DiscoveryClient discoveryClient;

  public Organization getOrganization(String organizationId) {
    RestTemplate restTemplate = new RestTemplate();
    List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");

    if (instances.size() == 0) {
      return null;
    }

    System.out.println(instances);

    String serviceUri = String.format("%s/v1/organizations/%s",
        instances.get(0).getUri().toString(), organizationId);

    ResponseEntity<Organization> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET,
        null, Organization.class);

    return restExchange.getBody();
  }
}
