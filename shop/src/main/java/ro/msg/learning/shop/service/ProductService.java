package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.service.exception.ProductException;

import java.util.List;


public interface ProductService {
    List<Product> findAllProducts();

    Product findById(Integer id) throws ProductException;

    Product createProduct(Product product, Integer productId, Integer supplierId);

    void deleteProduct(Integer id);

    Product updateProduct(Product product, Integer id, Integer productId, Integer supplierId);

    Supplier getSupplierById(Integer supplierId);

    ProductCategory getProductCategoryById(Integer productId);
}
