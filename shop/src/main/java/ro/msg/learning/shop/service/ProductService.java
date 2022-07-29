package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.exception.ProductCategoryException;
import ro.msg.learning.shop.service.exception.ProductException;
import ro.msg.learning.shop.service.exception.SupplierException;


import java.util.List;


public interface ProductService {
    List<ProductDto> findAllProducts();

    ProductDto findById(Integer id) throws ProductException;

    Product createProduct(ProductDto productDto) throws ProductCategoryException, SupplierException;
}
