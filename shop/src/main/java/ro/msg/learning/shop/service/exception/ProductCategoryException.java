package ro.msg.learning.shop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductCategoryException extends RuntimeException {
    public ProductCategoryException(String message) {
        super(message);
    }
}
