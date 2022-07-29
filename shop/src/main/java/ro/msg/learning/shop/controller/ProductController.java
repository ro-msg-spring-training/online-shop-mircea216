package ro.msg.learning.shop.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;
import ro.msg.learning.shop.service.exception.ProductException;

import java.util.List;


public interface ProductController {
    List<ProductDto> findAllProducts();

    ProductDto findById(Integer id) throws ProductException;

    ResponseEntity<String> createProduct(ProductToSaveDto productDto);

    ResponseEntity<String> deleteProduct(Integer id);

}
