package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.CategoryDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.CategoryMapper;
import com.stefanogiuseppe.carsharing.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Vehicles Categories Controller", description = "This controller allows create, read, update and delete operations on Vehicles Categories")
@RequestMapping(path = "/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /*
    private ModelMapper categoryMapper;
    @Autowired
    public CategoryController(ModelMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    */
    @Autowired
    private CategoryMapper categoryMapper;
    @PostMapping("")
    @Operation(description = "Adds a new category to the repository")
    public CategoryDTO addCategory(@Parameter(description = "The new administrator in a JSON format") @RequestBody CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        CategoryEntity categoryEntity1 = categoryService.saveCategory(categoryEntity);
        CategoryDTO categoryDTO1 = categoryMapper.toDto(categoryEntity1);
        return categoryDTO1;
    }

    @GetMapping("")
    @Operation(description = "Returns all categories of the repository")
    public List<CategoryDTO> getAllCategory(){
        List<CategoryEntity> categoryEntities=categoryService.getAllCategory();
        List<CategoryDTO> categoryDTO = categoryEntities.stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
        return categoryDTO;
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns a category inside the repository with the specified id")
    public CategoryDTO getCategoryById(@Parameter(description="The id of the requested category") @PathVariable Long id){
        CategoryEntity categoryEntity = categoryService.findById(id);
        CategoryDTO categoryDTO = categoryMapper.toDto(categoryEntity);
        return categoryDTO;
    }

    @PutMapping("/{id}")
    @Operation(description = "Updates all information of an existing category")
    public CategoryDTO updatePutCategory(@Parameter(description="The id of the vehicle category to update") @PathVariable Long id,@Parameter(description = "The updated vehicle category") @RequestBody CategoryDTO categoryDTO){

        CategoryEntity categoryEntity=categoryService.updateCategory(id, categoryDTO);

        CategoryDTO categoryDTO1 = categoryMapper.toDto(categoryEntity);

        return categoryDTO1;
    }
    @PatchMapping("/{id}")
    @Operation(description = "Updates some information of an existing vehicle category")
    public CategoryDTO updatePatchCategory(@Parameter(description="The id of the category to update") @PathVariable Long id, @Parameter(description = "The updated information of vehicle category") @RequestBody CategoryDTO categoryDTO){

        CategoryEntity categoryEntity=categoryService.updateCategory(id, categoryDTO);

        CategoryDTO categoryDTO1 = categoryMapper.toDto(categoryEntity);

        return categoryDTO1;
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletes a vehicle category from the repository")
    public String deleteCategory(@Parameter(description = "The id of the category to delete") @PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category " + id + " deleted";
    }

    @GetMapping("has_vehicles_booked/{categoryName}")
    @Operation(description = "Returns true if there is at least one vehicle booked of this category, false otherwise")
    public Boolean isBooked(@Parameter(description = "The category name") @PathVariable String categoryName){
        return categoryService.hasVehiclesBooked(categoryName);
    }
}

