package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exception.LocationStockException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SingleLocationStrategy implements OrderStrategy {
    private static final String UNFOUND_STOCK = "No stock with a suitable location!";
    private final StockRepository stockRepository;

    public SingleLocationStrategy(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getStocksByOrders(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(orderDetail -> {
                            if (selectSuitableLocationStock(orderDetail.getQuantity(),
                                    orderDetail.getProductOrder().getId()).isPresent())
                                return selectSuitableLocationStock(orderDetail.getQuantity(),
                                        orderDetail.getProductOrder().getId()).get();
                            else throw new LocationStockException(UNFOUND_STOCK);
                        }
                ).collect(Collectors.toList());

    }

    private Optional<Stock> selectSuitableLocationStock(Integer quantity, Integer productId) {
        return stockRepository.findAll()
                .stream()
                .filter(currentStock -> currentStock.getProductStock().getId().equals(productId) &&
                        currentStock.getQuantity() >= quantity)
                .findFirst();
    }
}
