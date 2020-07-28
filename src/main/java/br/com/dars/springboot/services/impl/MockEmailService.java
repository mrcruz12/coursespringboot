package br.com.dars.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Simulando o envio de emails!");
        LOG.info(simpleMailMessage.toString());
        LOG.info("Email enviado!");
    }

    @Override
    public void sendEmailHtml(MimeMessage mimeMessage) {
        LOG.info("Simulando o envio de emails!");
        LOG.info(mimeMessage.toString());
        LOG.info("Email enviado!");
    }
}
