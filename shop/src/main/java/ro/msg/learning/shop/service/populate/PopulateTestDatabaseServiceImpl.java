package ro.msg.learning.shop.service.populate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

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
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public void populateDatabaseWithMockData() {
        Supplier supplier = new Supplier("Apple");
        supplier.setId(1);
        supplierRepository.save(supplier);
        Supplier supplier1 = supplierRepository.getReferenceById(1);
        ProductCategory productCategory = productCategoryRepository.save(new ProductCategory("phone",
                "great"));
        productCategory.setId(1);
        Product firstProduct = new Product("Iphone12", "cool",
                BigInteger.valueOf(6000), 200.0, supplier1, productCategory, "http/");
        firstProduct.setId(1);
        productRepository.save(firstProduct);
        Product secondProduct = new Product("Iphone13", "cooler",
                BigInteger.valueOf(6500), 300.0, supplier1, productCategory, "http//");
        secondProduct.setId(2);
        productRepository.save(secondProduct);
        Location location1 = new Location("Emag", "Ro", "Cj", "Cj",
                "centralStreet");
        location1.setId(1);
        locationRepository.save(location1);
        Location location2 = new Location("Altex", "Ro", "Tm", "Tm",
                "central");
        location2.setId(2);
        locationRepository.save(location2);
        Location location3 = new Location("Flanco", "Ro", "Or", "Bh",
                "central");
        location3.setId(3);
        locationRepository.save(location3);
        Customer customer = new Customer("Alex", "Doe",
                "adoe", "pass", "adoe@gmail.com");
        customer.setId(1);
        customerRepository.save(customer);
        stockRepository.save(new Stock(location1, firstProduct, 15));
        stockRepository.save(new Stock(location2, firstProduct, 20));
        stockRepository.save(new Stock(location3, firstProduct, 10));
        stockRepository.save(new Stock(location1, secondProduct, 10));
        stockRepository.save(new Stock(location2, secondProduct, 10));
    }

    @Override
    public void depopulateDatabase() {
        stockRepository.deleteAll();
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
        locationRepository.deleteAll();
        productCategoryRepository.deleteAll();
        supplierRepository.deleteAll();
    }
}
