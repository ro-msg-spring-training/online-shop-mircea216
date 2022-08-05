package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ro.msg.learning.shop.configuration.mail.EmailTemplates;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final String ORDER_CONFIRMATION = "ORDER CONFIRMATION";
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;

    @Override
    public void sendEmailOfConfirmation(Order order) {
        Map<String, Object> optionalVariables = new HashMap<>();
        optionalVariables.put("recipientName", order.getCustomer().getFirstName());
        String replacedThymeleafTemplate = this.getReplacedThymeleafTemplate(optionalVariables,
                EmailTemplates.CONFIRMATION);
        try {
            mailSenderHandler(order, replacedThymeleafTemplate);
        } catch (MailException exception) {
            exception.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void mailSenderHandler(Order order, String replacedThymeleafTemplate) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(fromEmail);
        messageHelper.setTo(order.getCustomer().getEmailAddress());
        messageHelper.setSubject(ORDER_CONFIRMATION);
        messageHelper.setText(replacedThymeleafTemplate);
        mailSender.send(message);
    }

    private String getReplacedThymeleafTemplate(Map<String, Object> templateModel, EmailTemplates emailTemplate) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        return thymeleafTemplateEngine.process(emailTemplate.getTemplateName(), thymeleafContext);
    }
}
