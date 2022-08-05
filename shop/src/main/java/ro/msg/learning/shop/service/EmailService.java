package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Order;

public interface EmailService {
    void sendEmailOfConfirmation(Order order);
}
