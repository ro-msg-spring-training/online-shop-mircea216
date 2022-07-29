package ro.msg.learning.shop.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.ProductController;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.exception.ProductCategoryException;
import ro.msg.learning.shop.service.exception.ProductException;
import ro.msg.learning.shop.service.exception.SupplierException;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Override
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Integer id) throws ProductException {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductToSaveDto productDto) {
        productService.createProduct(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
