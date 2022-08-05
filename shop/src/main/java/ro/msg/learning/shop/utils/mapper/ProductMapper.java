package ro.msg.learning.shop.utils.mapper;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.dto.ProductToSaveDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

public class ProductMapper {
    public static ProductDto ProductToDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDto(product);
    }

    public static Product DtoToSaveToProduct(ProductToSaveDto productToSaveDto, ProductCategory productCategory,
                                             Supplier supplier) {
        return new Product(productToSaveDto, productCategory, supplier);
    }
}
