package br.com.dars.springboot.domain;

import br.com.dars.springboot.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment{
    private static final long serialVersionUID = 1L;

    private int paymentInstallments;

    public PaymentCard() {
    }

    public PaymentCard(Long id, PaymentStatus paymentStatus, Request request, int paymentInstallments) {
        super(id, paymentStatus, request);
        this.paymentInstallments = paymentInstallments;
    }

    public int getPaymentInstallments() {
        return paymentInstallments;
    }

    public void setPaymentInstallments(int paymentInstallments) {
        this.paymentInstallments = paymentInstallments;
    }
}
