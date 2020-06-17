package br.com.dars.springboot.dto;

import br.com.dars.springboot.domain.Product;
import org.springframework.data.domain.Page;

import java.io.Serializable;

public class ProductDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public static Page<ProductDTO> convertForProductDTOPage(Page<Product> list) {
        return list.map(obj -> new ProductDTO(obj));

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
