package br.com.dars.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private JavaMailSender mailSender;


    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);


    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Simulando o envio de emails!");
        mailSender.send(simpleMailMessage);
        LOG.info("Email enviado!");
    }
}
