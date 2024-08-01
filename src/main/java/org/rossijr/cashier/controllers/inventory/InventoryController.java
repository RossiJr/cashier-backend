package org.rossijr.cashier.controllers.inventory;

import org.rossijr.cashier.exceptions.InventoriesLimitReachedException;
import org.rossijr.cashier.exceptions.ObjectNotFoundException;
import org.rossijr.cashier.models.organization.Inventory;
import org.rossijr.cashier.services.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations/{organizationID}/inventories")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;


    @PostMapping()
    public ResponseEntity<Object> createInventory(@PathVariable("organizationID") String id, @RequestBody Inventory inventory) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        if (inventory == null || inventory.getName() == null || inventory.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Inventory name is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            Inventory createdInventory = inventoryService.createInventory(orgId, inventory.getName());
            return ResponseEntity.ok(createdInventory);
        } catch (IllegalArgumentException | ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        } catch (InventoriesLimitReachedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while creating inventory, please try again later!");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getInventories(@PathVariable("organizationID") String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            List<Inventory> inventories = inventoryService.getInventories(orgId);
            return ResponseEntity.ok(inventories);
        } catch (IllegalArgumentException | ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while fetching inventories, please try again later!");
        }
    }
}
