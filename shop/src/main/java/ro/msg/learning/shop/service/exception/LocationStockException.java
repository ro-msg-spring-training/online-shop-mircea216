package ro.msg.learning.shop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such location or stock")

public class LocationStockException extends RuntimeException {
    public LocationStockException(String message) {
        super(message);
    }
}
