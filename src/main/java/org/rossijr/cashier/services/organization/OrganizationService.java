package org.rossijr.cashier.services.organization;

import org.rossijr.cashier.enums.organization.Status;
import org.rossijr.cashier.exceptions.InvalidDataException;
import org.rossijr.cashier.exceptions.ObjectNotFoundException;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.repository.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;


    public boolean isAllowedToAddMoreInventories(UUID organizationID) {
        Organization organization = organizationRepository.findById(organizationID).orElse(null);
        if (organization == null || isValidOrganization(organizationID)) {
            throw new ObjectNotFoundException("Organization {" + organizationID + "} not found");
        }
        return organization.getInventories().size() < organization.getMaxInventories();
    }


    public Organization createOrganization(Organization organization) {
        if (organization == null) {
            throw new InvalidDataException("Organization is required");
        } else if (organization.getName() == null || organization.getName().isEmpty()) {
            throw new InvalidDataException("Organization name is required");
        } else if (organization.getOrganizationEmail() == null || organization.getOrganizationEmail().isEmpty()) {
            throw new InvalidDataException("Organization email is required");
        } else if (organization.getOrganizationPhoneNumber() == null || organization.getOrganizationPhoneNumber().isEmpty()) {
            throw new InvalidDataException("Organization phone number is required");
        }

        if (!organizationRepository.findByOrganizationEmail(organization.getOrganizationEmail()).isEmpty()) {
            throw new InvalidDataException("Organization with email {" + organization.getOrganizationEmail() + "} already exists!");
        }

        Organization obj = new Organization();
        obj.setName(organization.getName());
        obj.setOrganizationEmail(organization.getOrganizationEmail());
        obj.setOrganizationPhoneNumber(organization.getOrganizationPhoneNumber());
        obj.setStatus(Status.ACTIVE);
        obj.setMaxInventories(1);
        obj.setMaxUsers(1);
        obj.setCreationDate(Calendar.getInstance());

        obj = organizationRepository.save(obj);
        return obj;
    }

    public Organization getOrganization(UUID id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public Boolean isValidOrganization(UUID id) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        return (organization != null && organization.getStatus() == Status.ACTIVE);
    }
}
