package org.rossijr.cashier.services.inventory;


import org.rossijr.cashier.exceptions.InventoriesLimitReachedException;
import org.rossijr.cashier.exceptions.ObjectNotFoundException;
import org.rossijr.cashier.models.organization.Inventory;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.repository.inventory.InventoryRepository;
import org.rossijr.cashier.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private OrganizationService organizationService;


    public Inventory createInventory(UUID organizationID, String name) {
        if (organizationID == null) {
            throw new IllegalArgumentException("Organization id is required");
        }

        Organization organization = organizationService.getOrganization(organizationID);
        if (organization == null) {
            throw new ObjectNotFoundException("Organization {" + organizationID + "} not found");
        }

        if(!organizationService.isAllowedToAddMoreInventories(organizationID)){
            throw new InventoriesLimitReachedException("Organization {" + organizationID + "} has reached the limit of inventories");
        }


        Inventory inventory = new Inventory();
        inventory.setName(name);
        inventory.setOrganization(organization);
        inventory.setCreatedAt(Calendar.getInstance());

        inventory = inventoryRepository.save(inventory);

        return inventory;
    }

    public List<Inventory> getInventories(UUID organizationID) {
        if (organizationID == null) {
            throw new IllegalArgumentException("Organization id is required");
        }

        Organization organization = organizationService.getOrganization(organizationID);
        if (organizationService.isValidOrganization(organizationID)) {
            throw new ObjectNotFoundException("Organization {" + organizationID + "} not found");
        }

        return organization.getInventories();
    }
}
