package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.CategoryDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    private ModelMapper categoryMapper;
    @Autowired
    public CategoryController(ModelMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = categoryMapper.map(categoryDTO, CategoryEntity.class);
        CategoryEntity categoryEntity1 = categoryService.saveCategory(categoryEntity);
        CategoryDTO categoryDTO1 = categoryMapper.map(categoryEntity1, CategoryDTO.class);
        return categoryDTO1;
    }

    @GetMapping("")
    public List<CategoryDTO> getAllCategory(){
        List<CategoryEntity> categoryEntities=categoryService.getAllCategory();
        List<CategoryDTO> categoryDTO = categoryEntities.stream()
                .map(category -> categoryMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return categoryDTO;
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        CategoryEntity categoryEntity = categoryService.findById(id);
        CategoryDTO categoryDTO = categoryMapper.map(categoryEntity, CategoryDTO.class);
        return categoryDTO;
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){

        CategoryEntity categoryEntity=categoryService.updateCategory(id, categoryDTO);

        CategoryDTO categoryDTO1 = categoryMapper.map(categoryEntity, CategoryDTO.class);

        return categoryDTO1;
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category " + id + " deleted";
    }
}

