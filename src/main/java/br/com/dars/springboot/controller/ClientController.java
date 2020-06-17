package br.com.dars.springboot.controller;

import br.com.dars.springboot.domain.Client;
import br.com.dars.springboot.dto.ClientDTO;
import br.com.dars.springboot.dto.NewClientDTO;
import br.com.dars.springboot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

//    @GetMapping
//    public ResponseEntity<List<Client>> findAll(){
//        return ResponseEntity.ok().body(clientService.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Client> findByIdcc(@PathVariable Long id){
//        Client client = clientService.findById(id);
//
//            return ResponseEntity.ok().body(client);
//    }
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Client> list = clientService.findAllPage(page, size, orderBy, direction);
        Page<ClientDTO> listDto = ClientDTO.convertForClientDTOPage(list);
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<Client> save(@Valid @RequestBody NewClientDTO clientDto) {
        Client client = clientService.save(clientService.newClientFromDTO(clientDto));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid  @RequestBody ClientDTO clientDto) {

        System.out.println(clientDto.getName() +" - " + clientDto.getEmail());
        Client client = clientService.update(id, clientDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

