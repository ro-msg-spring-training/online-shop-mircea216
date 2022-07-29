package ro.msg.learning.shop.utils.mapper;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

public class ProductMapper {
    public static ProductDto ProductToDto(Product product) {
        if (product == null)
            return null;
        return new ProductDto(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getWeight(), product.getImageUrl(),
                product.getProductCategory().getId(),
                product.getProductCategory().getName(),
                product.getProductCategory().getDescription(),
                product.getSupplier().getId(), product.getSupplier().getName());
    }

    public static Product DtoToProduct(ProductDto productDto) {
        if (productDto == null)
            return null;
        ProductCategory productCategory = new ProductCategory(productDto.getProductCategoryName(),
                productDto.getProductCategoryDescription());
        productCategory.setId(productDto.getProductCategoryId());
        Supplier supplier = new Supplier(productDto.getSupplierName());
        supplier.setId(productDto.getSupplierId());
        Product product = new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(),
                productDto.getWeight(), supplier, productCategory, productDto.getImageUrl());
        product.setId(productDto.getId());
        return product;
    }
}
