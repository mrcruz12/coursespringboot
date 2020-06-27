package br.com.dars.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class RequestItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private RequestItemPK id = new RequestItemPK();
    private Double discount;
    private Integer amount;
    private Double price;

    public RequestItem() {
    }

    public RequestItem(Request request, Product product, Double discount, Integer amount, Double price) {
        id.setRequest(request);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public RequestItemPK getId() {
        return id;
    }

    public void setId(RequestItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getSubTotal(){
        return (price - discount) * amount;
    }

    @JsonIgnore
    public Request getRequest(){
        return id.getRequest();
    }

    public void setRequest(Request request){
        this.id.setRequest(request);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        this.id.setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItem that = (RequestItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(getProduct().getName());
        builder.append(", quantidade: ");
        builder.append(getAmount());
        builder.append(", preço unitário: ");
        builder.append(nf.format(getPrice()));
        builder.append(", subTotal: ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }
}
