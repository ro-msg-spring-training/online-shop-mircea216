package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.EmailService;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.strategy.OrderStrategy;
import ro.msg.learning.shop.utils.mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderStrategy orderStrategy;
    private final EmailService emailService;

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = OrderMapper.DtoToOrder(orderDto);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDto.getOrderedProducts().forEach(stockDto -> configureOrderDetails(order, orderDetails, stockDto));
        List<Stock> stocks = orderStrategy.getStocksByOrders(orderDetails);
        shipProductsFromStocks(orderDetails, stocks);
        orderRepository.save(order);
        orderDetails.forEach(orderDetailRepository::save);
        emailService.sendEmailOfConfirmation(order);
        return order;
    }

    private void shipProductsFromStocks(List<OrderDetail> orderDetails, List<Stock> stocks) {
        for (Stock stock : stocks) {
            for (OrderDetail orderDetail : orderDetails)
                if (stock.getProductStock().getId().equals(orderDetail.getProductOrder().getId())) {
                    stock.setQuantity(stock.getQuantity() - orderDetail.getQuantity());
                    stockRepository.save(stock);
                }
        }
    }

    private void configureOrderDetails(Order order, List<OrderDetail> orderDetails, StockDto stockDto) {
        orderDetails.add(new OrderDetail(order,
                productRepository.getReferenceById(stockDto.getProductId()),
                stockDto.getQuantity()));
    }
}
