package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.CategoryDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }

    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public CategoryEntity updateCategory(Long id, CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = findById(id);
        categoryDTO.setId(categoryEntity.getId());

        BeanUtils.copyProperties(categoryDTO, categoryEntity, getNullPropertyNames(categoryDTO));
        return categoryRepository.save(categoryEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(categoryEntity);
    }

}
