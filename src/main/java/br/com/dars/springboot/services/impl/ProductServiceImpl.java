package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.domain.Product;
import br.com.dars.springboot.domain.Request;
import br.com.dars.springboot.repository.CategoryRepository;
import br.com.dars.springboot.repository.ProductRepository;
import br.com.dars.springboot.repository.RequestRepository;
import br.com.dars.springboot.services.ProductService;
import br.com.dars.springboot.services.RequestService;
import br.com.dars.springboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Product findById(Long id) {
        Optional<Product> obj = productRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found Object! ID: "+id+", Tipo: "+Product.class.getName()));
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Page<Product> search(String name, List<Long> ids, Integer page, Integer size,  String sort, String direction){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort);
        List<Category> categories = categoryRepo.findAllById(ids);

        return productRepo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }
}
