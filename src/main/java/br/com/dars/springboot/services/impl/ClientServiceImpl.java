package br.com.dars.springboot.services.impl;


import br.com.dars.springboot.domain.Address;
import br.com.dars.springboot.domain.City;
import br.com.dars.springboot.domain.Client;
import br.com.dars.springboot.domain.enums.ClientType;
import br.com.dars.springboot.dto.ClientDTO;
import br.com.dars.springboot.dto.NewClientDTO;
import br.com.dars.springboot.repository.AddressRepository;
import br.com.dars.springboot.repository.CityRepository;
import br.com.dars.springboot.repository.ClientRepository;
import br.com.dars.springboot.services.ClientService;
import br.com.dars.springboot.services.exceptions.DataIntegrityException;
import br.com.dars.springboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Override
    public Client findById(Long id) {
        Optional<Client> obj = clientRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found Object! ID: "+id+", Tipo: "+Client.class.getName()));
    }

    @Transactional
    @Override
    public Client save(Client client) {
        client = clientRepo.save(client);
        addressRepo.saveAll(client.getAddresses());
        return client;
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client update(Long id, ClientDTO objDto) {
        Client client = findById(id);
        updateData(client, objDto);

        return clientRepo.save(client);
    }

    private void updateData(Client client, ClientDTO obj) {
        client.setName(obj.getName());
        client.setEmail(obj.getEmail());
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        try {
            clientRepo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It is not possible to delete by has entities realations");
        }

    }

    public Page<Client> findAllPage(Integer page, Integer size, String sort, String direction){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort);
        return clientRepo.findAll(pageRequest);
    }

    @Transactional
    @Override
    public Client newClientFromDTO(NewClientDTO clientDto) {
        System.out.println(clientDto.getClientType());
        Client client = new Client(null, clientDto.getName(), clientDto.getEmail(), clientDto.getCpfOrCnpj(), ClientType.toEnum(clientDto.getClientType()));
        Optional<City> city = cityRepo.findById(clientDto.getCityId());
        Address address = new Address(null, clientDto.getPublicPlace(), clientDto.getNumber(), clientDto.getComplement(), clientDto.getNeighborhood(), clientDto.getZipCode(), client, city.get()  );
        client.getAddresses().add(address);
        client.getFones().add(clientDto.getFone1());

        if(clientDto.getFone2() != null)
            client.getFones().add(clientDto.getFone2());
        if(clientDto.getFone3() != null)
            client.getFones().add(clientDto.getFone3());

        return client;
    }

    @Override
    public Client fromDTO(ClientDTO clientDto) {
       return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null);
    }

}
