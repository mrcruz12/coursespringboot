package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.dto.CategoryDTO;
import br.com.dars.springboot.repository.CategoryRepository;
import br.com.dars.springboot.services.CategoryService;
import br.com.dars.springboot.services.exceptions.DataIntegrityException;
import br.com.dars.springboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Category findById(Long id) {
        Optional<Category> obj = categoryRepo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found Object! ID: "+id+", Tipo: "+Category.class.getName()));
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category update(Long id, CategoryDTO objDto) {
        Category category = findById(id);
        updateData(category, objDto);

        return categoryRepo.save(category);
    }

    private void updateData(Category category, CategoryDTO obj) {
        category.setName(obj.getName());

    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        try {
            categoryRepo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It is not possible to delete a category that has products");
        }

    }

    public Page<Category> findAllPage(Integer page, Integer size,  String sort, String direction){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort);
        return categoryRepo.findAll(pageRequest);
    }

    @Override
    public Category fromDTO(CategoryDTO categoryDto) {
        return new Category(categoryDto.getId(), categoryDto.getName());
    }

}
