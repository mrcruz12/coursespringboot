package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.Request;
import br.com.dars.springboot.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

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

}
