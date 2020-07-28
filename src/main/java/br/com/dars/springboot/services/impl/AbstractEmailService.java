package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.Request;
import br.com.dars.springboot.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Request request){
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromRequest(request);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request request){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(request.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Order confirmed: "+request.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(request.toString());
        return sm;
    }

    protected String prepareHtmlMessageFromRequest(Request request) {
        Context context = new Context();
        context.setVariable("request", request);
        return templateEngine.process("email/OrderConfirmation", context);
    }

    public void sendOrderConfirmationEmailHtml(Request request){
        try {
            MimeMessage mimeMessage = prepareMimeMessageFromRequest(request);
            sendEmailHtml(mimeMessage);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(request);
        }

    }

    protected MimeMessage prepareMimeMessageFromRequest(Request request) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(request.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Order confirmed: "+request.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(prepareHtmlMessageFromRequest(request), true);
        return mimeMessage;
    }
}
