package br.com.dars.springboot.dto;

import br.com.dars.springboot.services.validation.InsertClient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@InsertClient
public class NewClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "mandatory")
    @Length(min = 5, max = 80,message = "length between 5 and 80 characters")
    private String name;
    @NotEmpty(message = "mandatory")
    @Email(message = "Invalid email")
    private String email;
    private String cpfOrCnpj;
    private Integer clientType;
    @NotEmpty(message = "mandatory")
    private String password;

    @NotEmpty(message = "mandatory")
    private String publicPlace;
    @NotEmpty(message = "mandatory")
    private String number;
    private String complement;
    private String neighborhood;
    @NotEmpty(message = "mandatory")
    private String zipCode;

    @NotEmpty(message = "mandatory")
    private String fone1;
    private String fone2;
    private String fone3;

    private Long cityId;

    public NewClientDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getFone3() {
        return fone3;
    }

    public void setFone3(String fone3) {
        this.fone3 = fone3;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
