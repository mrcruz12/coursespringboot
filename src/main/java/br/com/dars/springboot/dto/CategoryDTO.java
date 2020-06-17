package br.com.dars.springboot.dto;

import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.domain.Product;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "mandatory")
    @Length(min = 5, max = 80,message = "length between 5 and 80 characters")
    private String name;

    private List<Product> products = new ArrayList<>();

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public static Page<CategoryDTO> convertForCategoryDTOPage(Page<Category> list) {
        Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj));
        return listDto;
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

    public static List<CategoryDTO> convertForCategoryDTO(List<Category> list){
        List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
        return listDto;
    }

}
