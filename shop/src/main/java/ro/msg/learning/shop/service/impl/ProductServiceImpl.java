package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.exception.ProductCategoryException;
import ro.msg.learning.shop.service.exception.ProductException;
import ro.msg.learning.shop.service.exception.SupplierException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCT_EXCEPTION = "No product with this id";
    private static final String CATEGORY_EXCEPTION = "No category with this ID";
    private static final String SUPPLIER_EXCEPTION = "No supplier with this ID";
    private static final String MISMATCH = "IDs mismatch";
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) throws ProductException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(PRODUCT_EXCEPTION));
    }

    @Override
    public Product createProduct(Product product, Integer productId, Integer supplierId) {
        existsProductCategory(productId);
        existsSupplier(supplierId);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id))
            throw new ProductException(PRODUCT_EXCEPTION);
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product, Integer id, Integer productId, Integer supplierId) {
        if (!id.equals(product.getId()))
            throw new ProductException(MISMATCH);
        existsProductCategory(productId);
        existsSupplier(supplierId);
        return productRepository.save(product);
    }

    public Supplier getSupplierById(Integer supplierId) {
        return supplierRepository.getReferenceById(supplierId);
    }

    public ProductCategory getProductCategoryById(Integer productId) {
        return productCategoryRepository.getReferenceById(productId);
    }

    private void existsSupplier(Integer supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new SupplierException(SUPPLIER_EXCEPTION);
        }
    }

    private void existsProductCategory(Integer productId) {
        if (!productCategoryRepository.existsById(productId)) {
            throw new ProductCategoryException(CATEGORY_EXCEPTION);
        }
    }
}
