package br.com.dars.springboot.dto;

import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.domain.Client;
import br.com.dars.springboot.services.validation.UpdateClient;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@UpdateClient
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "mandatory")
    @Length(min = 5, max = 80,message = "length between 5 and 80 characters")
    private String name;
    @NotEmpty(message = "mandatory")
    @Email(message = "Invalid email")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<ClientDTO> convertForClientDTO(List<Client> list){
        List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return listDto;
    }
    public static Page<ClientDTO> convertForClientDTOPage(Page<Client> list) {
        Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
        return listDto;
    }
}
