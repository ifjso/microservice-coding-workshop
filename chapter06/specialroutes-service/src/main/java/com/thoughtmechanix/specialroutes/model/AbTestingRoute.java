package com.thoughtmechanix.specialroutes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abtesting")
public class AbTestingRoute {

  @Id
  @Column(name = "service_name", nullable = false)
  private String serviceName;

  @Column(name = "active", nullable = false)
  private String active;

  @Column(name = "endpoint", nullable = false)
  private String endpoint;

  @Column(name = "weight", nullable = false)
  private Integer weight;

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}
