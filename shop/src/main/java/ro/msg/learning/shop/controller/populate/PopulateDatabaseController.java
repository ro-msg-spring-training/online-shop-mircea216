package ro.msg.learning.shop.controller.populate;

import org.springframework.http.ResponseEntity;

public interface PopulateDatabaseController {
    ResponseEntity<String> populate();

    ResponseEntity<String> depopulate();
}
