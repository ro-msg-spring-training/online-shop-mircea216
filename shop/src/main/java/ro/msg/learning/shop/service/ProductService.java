package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.exception.ProductException;

import java.util.List;


public interface ProductService {
    List<ProductDto> findAllProducts();

    ProductDto findById(Integer id) throws ProductException;

    Product createProduct(ProductToSaveDto productDto);

    void deleteProduct(Integer id);

    Product updateProduct(ProductToSaveDto productDto, Integer id);
}
