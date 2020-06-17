package br.com.dars.springboot.controller;

import br.com.dars.springboot.controller.utils.URL;
import br.com.dars.springboot.domain.Category;
import br.com.dars.springboot.domain.Product;
import br.com.dars.springboot.domain.Request;
import br.com.dars.springboot.dto.CategoryDTO;
import br.com.dars.springboot.dto.ProductDTO;
import br.com.dars.springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping
//    public ResponseEntity<List<Product>> findAll() {
//        return ResponseEntity.ok().body(productService.findAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);

    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Long> ids = URL.decodeLongList(categories);
        Page<Product> list = productService.search(name, ids, page, size, orderBy, direction);
        Page<ProductDTO> listDto = ProductDTO.convertForProductDTOPage(list);
        return ResponseEntity.ok().body(listDto);
    }
}
