package ro.msg.learning.shop.configuration.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Getter
public enum EmailTemplates {
    CONFIRMATION("Confirmation email", "email.html");
    private final String name;
    private final String templateName;
    private static final String EMAIL_TEMPLATES_FOLDER = "templates/email.html";

    public String getTemplate() throws IOException {
        return new String(new ClassPathResource(EMAIL_TEMPLATES_FOLDER)
                .getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);
    }
}
