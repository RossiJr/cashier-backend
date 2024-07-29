package org.rossijr.cashier.controllers.organization;

import org.rossijr.cashier.exceptions.InvalidDataException;
import org.rossijr.cashier.models.organization.Inventory;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.models.product.Product;
import org.rossijr.cashier.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/{organizationID}/inventories")
    public ResponseEntity<Object> createInventory(@PathVariable("organizationID") String id, @RequestBody Inventory inventory) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        if (inventory == null || inventory.getName() == null || inventory.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Inventory name is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            Organization organization = organizationService.getOrganization(orgId);
            if (organization == null) {
                return ResponseEntity.notFound().build();
            }
            Inventory createdInventory = organizationService.createInventory(organization, inventory.getName());
            return ResponseEntity.ok(createdInventory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        }
    }


    @PostMapping("/{organizationID}/products")
    public ResponseEntity<Object> createProduct(@PathVariable("organizationID") String id, @RequestBody Product product) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            Organization organization = organizationService.getOrganization(orgId);
            if (organization == null) {
                return ResponseEntity.notFound().build();
            }
            Product createdProduct = organizationService.createProduct(organization.getId(), product);
            return ResponseEntity.ok(createdProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        }
    }

    @GetMapping("/{organizationID}/products")
    public ResponseEntity<Object> getProducts(@PathVariable("organizationID") String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            if (!organizationService.isValidOrganization(orgId)) {
                return ResponseEntity.notFound().build();
            }
            List<Product> products = organizationService.getProductsByOrganization(orgId);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        }
    }

}
