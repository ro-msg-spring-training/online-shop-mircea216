package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDto;


import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProducts();
}
