package ro.msg.learning.shop.service.impl;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.exception.ProductException;
import ro.msg.learning.shop.utils.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String EXCEPTION_MESSAGE = "No product with this id";
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}
