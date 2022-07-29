package ro.msg.learning.shop.service.impl;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;
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
import java.util.Optional;
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
    public Product createProduct(ProductToSaveDto productDto) {
        if (!productCategoryRepository.existsById(productDto.getProductCategoryId())) {
            throw new ProductCategoryException(CATEGORY_EXCEPTION);
        }
        if (!supplierRepository.existsById(productDto.getSupplierId())) {
            throw new SupplierException(CATEGORY_EXCEPTION);
        }
        ProductCategory productCategory = productCategoryRepository.getReferenceById(productDto.getProductCategoryId());
        Supplier supplier = supplierRepository.getReferenceById(productDto.getSupplierId());
        return productRepository.save(ProductMapper.DtoToSaveToProduct(productDto, productCategory, supplier));
    }

    @Override
    public void deleteProduct(Integer id) {

    }
}
