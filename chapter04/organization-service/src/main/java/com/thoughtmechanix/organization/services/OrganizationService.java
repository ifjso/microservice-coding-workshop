package com.thoughtmechanix.organization.services;

import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

  @Autowired
  private OrganizationRepository organizationRepository;

  public Organization getOrganization(String organizationId) {
    Optional<Organization> organization = organizationRepository.findById(organizationId);
    if (!organization.isPresent()) {
      throw new NullPointerException("organizationId-" + organizationId);
    }

    return organization.get();
  }

  public void saveOrganization(Organization organization) {
    organization.setId(UUID.randomUUID().toString());
    organizationRepository.save(organization);
  }

  public void updateOrganization(Organization organization) {
    organizationRepository.save(organization);
  }

  public void deleteOrganization(Organization organization) {
    organizationRepository.delete(organization);
  }
}
