package ro.msg.learning.shop.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategy.MostAbundantLocationStrategy;
import ro.msg.learning.shop.service.strategy.OrderStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

@Configuration
@RequiredArgsConstructor
public class OrderStrategyConfiguration {
    private final StockRepository stockRepository;
    @Value("${order.strategy}")
    private EStrategyPicker orderStrategy;

    @Bean
    public OrderStrategy applyOrderStrategy() {
        switch (orderStrategy) {
            case SINGLE_LOCATION:
                return new SingleLocationStrategy(stockRepository);
            case MOST_ABUNDANT_LOCATION:
                return new MostAbundantLocationStrategy(stockRepository);
            default:
                return null;
        }
    }
}
