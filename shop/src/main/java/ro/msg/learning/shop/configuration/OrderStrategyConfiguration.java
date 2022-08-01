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
    private static final String SINGLE_LOCATION = "singleLocation";
    private final StockRepository stockRepository;
    @Value("${order.strategy}")
    private String orderStrategy;

    @Bean
    public OrderStrategy applyOrderStrategy() {
        if (SINGLE_LOCATION.equals(orderStrategy)) {
            return new SingleLocationStrategy(stockRepository);
        } else {
            return new MostAbundantLocationStrategy(stockRepository);
        }
    }
}
