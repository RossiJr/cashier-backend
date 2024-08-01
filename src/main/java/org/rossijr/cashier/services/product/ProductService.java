package org.rossijr.cashier.services.product;

import org.rossijr.cashier.enums.product.Category;
import org.rossijr.cashier.exceptions.ObjectNotFoundException;
import org.rossijr.cashier.models.organization.Organization;
import org.rossijr.cashier.models.product.Product;
import org.rossijr.cashier.repository.product.ProductRepository;
import org.rossijr.cashier.services.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrganizationService organizationService;

    public Product createProduct(UUID organizationID, Product product) {
        Product obj = new Product();
        obj.setName(product.getName());
        Organization organization = organizationService.getOrganization(organizationID);
        if (organizationService.isValidOrganization(organizationID)) {
            throw new ObjectNotFoundException("Organization {" + organizationID + "} not found");
        }
        obj.setOrganization(organization);
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

        obj.setAddedDate(Calendar.getInstance());

        return productRepository.save(obj);
    }

    public List<Product> getProducts(UUID organizationID) {
        if (organizationService.isValidOrganization(organizationID)) {
            throw new ObjectNotFoundException("Organization {" + organizationID + "} not found");
        }
        return productRepository.findByOrganizationId(organizationID);
    }
}
