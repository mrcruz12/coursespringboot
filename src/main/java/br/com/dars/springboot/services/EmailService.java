package br.com.dars.springboot.services;

import br.com.dars.springboot.domain.Request;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    public void sendOrderConfirmationEmail(Request request);

    public void sendEmail(SimpleMailMessage simpleMailMessage);

    public void sendOrderConfirmationEmailHtml(Request request);

    public void sendEmailHtml(MimeMessage mimeMessage);
}
