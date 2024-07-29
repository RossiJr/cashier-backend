package org.rossijr.cashier.repository.organization;

import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.models.projection.OrganizationBasicProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID>{

    @Query("SELECT o.id as id FROM Organization o WHERE o.organizationEmail = :organizationEmail")
    List<OrganizationBasicProjection> findByOrganizationEmail(String organizationEmail);

    @Query("SELECT o.id as id FROM Organization o WHERE o.id = :id")
    OrganizationBasicProjection findByIdBasic(UUID id);
}
