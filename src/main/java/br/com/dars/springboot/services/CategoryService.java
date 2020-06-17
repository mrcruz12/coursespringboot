package br.com.dars.springboot.services;


import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    public Category findById(Long id);

    public Category save(Category category);

    public List<Category> findAll();

    public Category update(Long id, CategoryDTO category);

    public void deleteById(Long id);

    public Page<Category> findAllPage(Integer page, Integer size, String direction, String sort);

    public Category fromDTO(CategoryDTO categoryDto);
}
