package org.rossijr.cashier.controllers.organization;

import org.rossijr.cashier.exceptions.InvalidDataException;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Object> createOrganization(@RequestBody Organization organization) {
        Organization createdOrganization;
        try {
            createdOrganization = organizationService.createOrganization(organization);
        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(createdOrganization);
    }

    @GetMapping("/{organizationID}")
    public ResponseEntity<Object> getOrganization(@PathVariable("organizationID") String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            Organization organization = organizationService.getOrganization(orgId);
            if (organization == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(organization);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        }
    }

}
