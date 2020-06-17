package br.com.dars.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    private Payment payment;


    @ManyToOne
    private Client client;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.request")
    private Set<RequestItem> requestItems = new HashSet<>();

    public Request() {
    }

    public Request(Long id, Date instant, Client client, Address deliveryAddress) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalValue(){
        double sum = 0.0;
        for (RequestItem r: requestItems){
            sum += r.getSubTotal();
        }
        return sum;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @OneToMany(mappedBy = "id.request")
    public Set<RequestItem> getRequestItems() {
        return requestItems;
    }

    public void setRequestItems(Set<RequestItem> requestItems) {
        this.requestItems = requestItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id.equals(request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
