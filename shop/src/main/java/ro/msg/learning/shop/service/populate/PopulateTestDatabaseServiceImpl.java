package ro.msg.learning.shop.service.populate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class PopulateTestDatabaseServiceImpl implements PopulateTestDatabaseService {
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;

    @Override
    public void populateDatabaseWithMockData() {
        Supplier supplier = supplierRepository.save(new Supplier("Apple"));
        ProductCategory productCategory = productCategoryRepository.save(new ProductCategory("phone",
                "great"));
        Product firstProduct = productRepository.save(new Product("Iphone12", "cool",
                BigInteger.valueOf(6000), 200.0, supplier, productCategory, "http/"));
        Product secondProduct = productRepository.save(new Product("Iphone13", "cooler",
                BigInteger.valueOf(6500), 300.0, supplier, productCategory, "http//"));
        Location location1 = locationRepository.save(new Location("Emag", "Ro", "Cj", "Cj",
                "centralStreet"));
        Location location2 = locationRepository.save(new Location("Altex", "Ro", "Tm", "Tm",
                "central"));
        Location location3 = locationRepository.save(new Location("Flanco", "Ro", "Or", "Bh",
                "central"));
        customerRepository.save(new Customer("Alex", "Doe",
                "adoe", "pass", "adoe@gmail.com"));
        stockRepository.save(new Stock(location1, firstProduct, 15));
        stockRepository.save(new Stock(location2, firstProduct, 20));
        stockRepository.save(new Stock(location3, firstProduct, 10));
        stockRepository.save(new Stock(location1, secondProduct, 10));
        stockRepository.save(new Stock(location2, secondProduct, 10));
    }

    @Override
    public void depopulateDatabase() {
        customerRepository.deleteAll();
        stockRepository.deleteAll();
        locationRepository.deleteAll();
        productRepository.deleteAll();
        productCategoryRepository.deleteAll();
        supplierRepository.deleteAll();
    }
}
