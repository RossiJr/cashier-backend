package org.rossijr.cashier.controllers.product;

import org.rossijr.cashier.exceptions.ObjectNotFoundException;
import org.rossijr.cashier.models.product.Product;
import org.rossijr.cashier.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations/{organizationID}/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@PathVariable("organizationID") String id, @RequestBody Product product) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Product name is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            Product createdProduct = productService.createProduct(orgId, product);
            return ResponseEntity.ok(createdProduct);
        } catch (IllegalArgumentException | ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while creating product, please try again later!");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(@PathVariable("organizationID") String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("Organization id is required");
        }
        try {
            UUID orgId = UUID.fromString(id);
            List<Product> products = productService.getProducts(orgId);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException | ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid organization id!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while fetching products, please try again later!");
        }
    }
}
