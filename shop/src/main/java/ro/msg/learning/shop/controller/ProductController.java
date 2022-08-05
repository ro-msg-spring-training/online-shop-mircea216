package ro.msg.learning.shop.controller;


import org.springframework.http.ResponseEntity;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;

import java.util.List;


public interface ProductController {
    List<ProductDto> findAllProducts();

    ProductDto findById(Integer id);

    ResponseEntity<String> createProduct(ProductToSaveDto productDto);

    ResponseEntity<String> deleteProduct(Integer id);

    ResponseEntity<String> updateProduct(ProductToSaveDto productDto, Integer id);
}
