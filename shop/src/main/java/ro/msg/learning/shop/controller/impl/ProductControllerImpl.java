package ro.msg.learning.shop.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.controller.ProductController;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.exception.ProductException;

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
}
