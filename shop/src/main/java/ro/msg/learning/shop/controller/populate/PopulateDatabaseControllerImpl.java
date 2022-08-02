package ro.msg.learning.shop.controller.populate;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.populate.PopulateTestDatabaseService;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
@Profile("test")
public class PopulateDatabaseControllerImpl implements PopulateDatabaseController {
    private static final String SUCCESSFUL_POPULATION = "The database has been successfully populated";
    private static final String SUCCESSFUL_DEPOPULATION = "The database has been successfully depopulated";
    private final PopulateTestDatabaseService populateTestDatabaseService;

    @PostMapping("/populate")
    public ResponseEntity<String> populate() {
        populateTestDatabaseService.populateDatabaseWithMockData();
        return new ResponseEntity<>(SUCCESSFUL_POPULATION, HttpStatus.OK);
    }

    @DeleteMapping("/depopulate")
    public ResponseEntity<String> depopulate() {
        populateTestDatabaseService.depopulateDatabase();
        return new ResponseEntity<>(SUCCESSFUL_DEPOPULATION, HttpStatus.OK);
    }
}
