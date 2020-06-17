package br.com.dars.springboot.controller;

import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.domain.Request;
import br.com.dars.springboot.dto.CategoryDTO;
import br.com.dars.springboot.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> findAll() {
        return ResponseEntity.ok().body(requestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable Long id) {
        Request request = requestService.findById(id);
        return ResponseEntity.ok().body(request);

    }

    @PostMapping
    public ResponseEntity<Void> save( @RequestBody Request request) {

        request = requestService.save(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
