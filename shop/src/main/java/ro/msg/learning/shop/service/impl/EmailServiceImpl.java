package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.service.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String ORDER_CONFIRMATION = "ORDER CONFIRMATION";
    private static final String ORDER_SUCCESS = "Your order of the product has been successfully!";
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender mailSender;

    @Override
    public void sendEmailOfConfirmation(String toEmail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(ORDER_CONFIRMATION);
            message.setText(ORDER_SUCCESS);
            mailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
