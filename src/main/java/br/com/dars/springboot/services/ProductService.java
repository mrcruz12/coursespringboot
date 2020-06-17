package br.com.dars.springboot.services;

import br.com.dars.springboot.domain.Product;
import br.com.dars.springboot.domain.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    public Product findById(Long id);

    public Product save(Product category);

    public List<Product> findAll();

    public Page<Product> search(String name, List<Long> ids, Integer page, Integer size,  String sort, String direction);

}
