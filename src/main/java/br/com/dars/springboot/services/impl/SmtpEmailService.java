package br.com.dars.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Enviando emails!");
        mailSender.send(simpleMailMessage);
        LOG.info("Email enviado!");
    }

    @Override
    public void sendEmailHtml(MimeMessage mimeMessage) {
        LOG.info("Enviando emails!");
        javaMailSender.send(mimeMessage);
        LOG.info("Email enviado!");
    }
}
