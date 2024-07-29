package org.rossijr.cashier.services.organization;

import org.rossijr.cashier.enums.organization.Status;
import org.rossijr.cashier.enums.product.Category;
import org.rossijr.cashier.exceptions.InvalidDataException;
import org.rossijr.cashier.models.organization.Inventory;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.models.product.Product;
import org.rossijr.cashier.repository.organization.InventoryRepository;
import org.rossijr.cashier.repository.organization.OrganizationRepository;
import org.rossijr.cashier.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;



    public Organization createOrganization(Organization organization){
        if (organization == null){
            throw new InvalidDataException("Organization is required");
        } else if (organization.getName() == null || organization.getName().isEmpty()){
            throw new InvalidDataException("Organization name is required");
        } else if (organization.getOrganizationEmail() == null || organization.getOrganizationEmail().isEmpty()){
            throw new InvalidDataException("Organization email is required");
        } else if (organization.getOrganizationPhoneNumber() == null || organization.getOrganizationPhoneNumber().isEmpty()){
            throw new InvalidDataException("Organization phone number is required");
        }

        if (!organizationRepository.findByOrganizationEmail(organization.getOrganizationEmail()).isEmpty()){
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

    public Organization getOrganization(UUID id){
        return organizationRepository.findById(id).orElse(null);
    }

    public Boolean isValidOrganization(UUID id){
        return organizationRepository.findByIdBasic(id) != null;
    }

    public Inventory createInventory(Organization organization, String name){
        if (organization.getMaxInventories() < inventoryRepository.findByOrganizationId(organization.getId()).size()) {
            throw new InvalidDataException("Organization {" + organization.getName() + "} has reached the maximum number of inventories");
        }

        Inventory inventory = new Inventory();
        inventory.setName(name);
        inventory.setOrganization(organization);
        inventory.setCreatedAt(Calendar.getInstance());

        inventory = inventoryRepository.save(inventory);
        organization.getInventories().add(inventory);
        organizationRepository.save(organization);

        return inventory;
    }

    public Product createProduct(UUID organizationID, Product product) {
        Product obj = new Product();
        obj.setName(product.getName());
        obj.setOrganization(new Organization(organizationID));
        if (product.getBarcode() != null && !product.getBarcode().isEmpty()) {
            obj.setBarcode(product.getBarcode());
        }
        if (product.getCategory() != null) {
            obj.setCategory(product.getCategory());
        } else {
            obj.setCategory(Category.NON_DEFINED);
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            obj.setDescription(product.getDescription());
        }

        return productRepository.save(obj);
    }

    public List<Product> getProductsByOrganization(UUID organizationID) {
        return productRepository.findByOrganizationId(organizationID);
    }
}
