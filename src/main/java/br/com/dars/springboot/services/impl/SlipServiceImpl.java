package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.PaymentSlip;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SlipServiceImpl {

    public void fillPaymentSplit(PaymentSlip paymentSlip, Date instant){
        Calendar c = Calendar.getInstance();
        c.setTime(instant);
        c.add(Calendar.DAY_OF_MONTH, 7);
        paymentSlip.setDueDate(c.getTime());
    }
}
