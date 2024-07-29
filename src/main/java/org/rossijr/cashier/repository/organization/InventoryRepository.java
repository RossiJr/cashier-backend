package org.rossijr.cashier.repository.organization;

import org.rossijr.cashier.models.organization.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    @Query("SELECT i FROM Inventory i WHERE i.organization.id = :organizationId")
    List<Inventory> findByOrganizationId(UUID organizationId);
}
