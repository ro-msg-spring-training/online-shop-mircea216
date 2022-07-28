package ro.msg.learning.shop.controller;

import ro.msg.learning.shop.dto.ProductDto;

import java.util.List;

public interface ProductController {
    List<ProductDto> findAllProducts();
}
