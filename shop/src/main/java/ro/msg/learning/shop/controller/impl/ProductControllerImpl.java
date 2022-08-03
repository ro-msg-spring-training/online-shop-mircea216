package ro.msg.learning.shop.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.controller.ProductController;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.utils.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    private static final String ID = "id";
    private static final String CREATE_SUCCESS = "Product successfully created";
    private static final String DELETE_SUCCESS = "Product successfully deleted";
    private static final String UPDATE_SUCCESS = "Product successfully updated";
    private final ProductService productService;

    @GetMapping
    @Override
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts().stream()
                .map(ProductMapper::ProductToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable(ID) Integer id) {
        return ProductMapper.ProductToDto(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductToSaveDto productDto) {
        productService.createProduct(ProductMapper.DtoToSaveToProduct(productDto,
                        productService.getProductCategoryById(productDto.getProductCategoryId()),
                        productService.getSupplierById(productDto.getSupplierId())),
                productDto.getProductCategoryId(), productDto.getSupplierId());
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deleteProduct(@PathVariable(ID) Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<String> updateProduct(@RequestBody ProductToSaveDto productDto,
                                                @PathVariable(ID) Integer id) {
        productService.updateProduct(ProductMapper.DtoToSaveToProduct(productDto,
                        productService.getProductCategoryById(productDto.getProductCategoryId()),
                        productService.getSupplierById(productDto.getSupplierId())), id,
                productDto.getProductCategoryId(),
                productDto.getSupplierId()
        );
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }
}
