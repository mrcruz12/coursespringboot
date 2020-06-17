package br.com.dars.springboot.services;

import br.com.dars.springboot.domain.Request;

import java.util.List;

public interface RequestService {

    public Request findById(Long id);

    public Request save(Request category);

    public List<Request> findAll();
}
