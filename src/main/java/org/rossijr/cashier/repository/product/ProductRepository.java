package org.rossijr.cashier.repository.product;

import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE p.organization.id = :organizationID")
    List<Product> findByOrganizationId(UUID organizationID);
}
