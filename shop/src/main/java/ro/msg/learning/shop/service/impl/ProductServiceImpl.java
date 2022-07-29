package ro.msg.learning.shop.service.impl;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDto;
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
import ro.msg.learning.shop.utils.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String EXCEPTION_MESSAGE = "No product with this id";
    private static final String CATEGORY_EXCEPTION = "No category with this ID";
    private static final String SUPPLIER_CATEGORY = "No supplier with this ID";
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::ProductToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Integer id) throws ProductException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(EXCEPTION_MESSAGE));
        return ProductMapper.ProductToDto(product);
    }

    @Override
    public Product createProduct(ProductDto productDto) throws ProductCategoryException, SupplierException {
        ProductCategory productCategory = productCategoryRepository.
                findById(productDto.getProductCategoryId()).orElseThrow(() -> new
                        ProductCategoryException(CATEGORY_EXCEPTION));
        Supplier supplier = supplierRepository.
                findById(productDto.getSupplierId()).orElseThrow(() ->
                        new SupplierException(SUPPLIER_CATEGORY));
        return productRepository.save(ProductMapper.DtoToProduct(productDto));
    }
}
