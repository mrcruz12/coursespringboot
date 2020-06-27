package br.com.dars.springboot.services;

import br.com.dars.springboot.domain.Request;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    public void sendOrderConfirmationEmail(Request request);

    public void sendEmail(SimpleMailMessage simpleMailMessage);
}
