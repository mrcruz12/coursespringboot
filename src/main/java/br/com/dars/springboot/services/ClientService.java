package br.com.dars.springboot.services;


import br.com.dars.springboot.domain.Client;
import br.com.dars.springboot.dto.ClientDTO;
import br.com.dars.springboot.dto.NewClientDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {

    public Client findById(Long id);

    public Client save(Client client);

    public List<Client> findAll();

    public Client update(Long id, ClientDTO clientDto);

    public void deleteById(Long id);

    public Page<Client> findAllPage(Integer page, Integer size, String direction, String sort);

    public Client fromDTO(ClientDTO clientDTO);

    public Client newClientFromDTO(NewClientDTO clientDto);
}