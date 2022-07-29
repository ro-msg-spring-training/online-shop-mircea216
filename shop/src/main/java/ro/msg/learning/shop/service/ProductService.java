package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.exception.ProductException;


import java.util.List;


public interface ProductService {
    List<ProductDto> findAllProducts();

    ProductDto findById(Integer id) throws ProductException;
}
