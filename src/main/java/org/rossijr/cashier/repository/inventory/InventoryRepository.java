package org.rossijr.cashier.repository.inventory;

import org.rossijr.cashier.models.organization.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    List<Inventory> findByOrganizationId(UUID organizationId);
}
