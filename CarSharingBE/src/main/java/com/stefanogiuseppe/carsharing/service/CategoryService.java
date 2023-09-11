package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.CategoryDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
import com.stefanogiuseppe.carsharing.repository.CategoryRepository;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelService modelService;

    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        List<CategoryEntity> categories = categoryRepository.findAll();
        boolean isOverlapping = false;
        categoryEntity.setDeleted(false);
        for(CategoryEntity category : categories){
            if(category.getDeleted() == false && category.getCategoryName().equals(categoryEntity.getCategoryName())) {
                //hai trovato un'altra tariffa per la stessa categoria da confrontare
                if(overlaps(categoryEntity,category)){
                    isOverlapping = true;
                    break;
                }
            }
        }
        if(!isOverlapping){
            return categoryRepository.save(categoryEntity);
        }else{
            return null;
        }

    }

    private boolean overlaps(CategoryEntity categoryEntity, CategoryEntity category) {
        // le date non si sovrappongono se categoryEntity ha tutte e due le date successive a quelle di category oppure se categoryEntity ha tutte e due le date precedenti a quelle di category
        int compareFromFrom = datesComparison(categoryEntity.getValidFrom(),category.getValidFrom(),0);
        int compareFromTo   = datesComparison(categoryEntity.getValidFrom(),category.getValidTo(),1);
        int compareToFrom   = datesComparison(categoryEntity.getValidTo(),category.getValidFrom(),2);
        int compareToTo = datesComparison(categoryEntity.getValidTo(),category.getValidTo(),3);
        if((compareFromFrom < 0 && compareFromTo < 0 && compareToFrom < 0 && compareToTo < 0) || (compareFromFrom > 0 && compareFromTo > 0 && compareToFrom > 0 && compareToTo > 0)){
            return false;
        }else{
            return true;
        }
    }
    private int datesComparison(Date date1, Date date2, int comparisonType){
        //comparisonType: 0 = FromFrom, 1 = FromTo, 2 = ToFrom, 3 = ToTo

        //compareTo. Se la prima data è precedente, restiuisce < 0, se la prima data è successiva restituisce > 0
        if(date1 == null && date2 == null){
            if(comparisonType == 0 || comparisonType == 3){
                return 0;
            }else if(comparisonType == 1){
                return -1;
            }else{
                return 1;
            }
        }else if(date1 == null && date2 != null){
            switch(comparisonType){
                case 0:
                    return -1;
                case 1:
                    return -1;
                case 2:
                    return 1;
                case 3:
                    return 1;
            }
        }else if(date1 != null && date2 == null){
            switch(comparisonType){
                case 0:
                    return 1;
                case 1:
                    return -1;
                case 2:
                    return 1;
                case 3:
                    return -1;
            }
        }else{
            return date1.compareTo(date2);
        }
        return 0;
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
        List<CategoryEntity> categories = categoryRepository.findAll();
        boolean isOverlapping = false;
        for(CategoryEntity category : categories){
            if(category.getId() != id && category.getDeleted() == false && category.getCategoryName().equals(categoryEntity.getCategoryName())) {
                //hai trovato un'altra tariffa per la stessa categoria da confrontare
                if(overlaps(categoryEntity,category)){
                    isOverlapping = true;
                    break;
                }
            }
        }
        if(!isOverlapping){
            return categoryRepository.save(categoryEntity);
        }else{
            return null;
        }

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
        categoryEntity.setDeleted(true);
        categoryRepository.save(categoryEntity);
    }

    public CategoryEntity findByNameAndDate(String categoryName, Date dateTimeStartRental) {
        List<CategoryEntity> categories = categoryRepository.findAll();
        for(CategoryEntity ce : categories){
            if(ce.getCategoryName().equals(categoryName)) {
                int compareValidFrom = ce.getValidFrom().compareTo(dateTimeStartRental);
                int compareValidTo = 0;
                if (ce.getValidTo() != null) {
                    compareValidTo = ce.getValidTo().compareTo(dateTimeStartRental);
                }
                if (compareValidFrom <= 0 && (ce.getValidTo() == null || compareValidTo >= 0)) {
                    return ce;
                }
            }
        }
        return null;
    }
    public boolean hasVehiclesBooked(String categoryName){
        List<ModelEntity> models = modelService.getAllModel();
        for(ModelEntity model : models){
            if(model.getCategoryName().equals(categoryName) && modelService.isBooked(model.getIdMod())){
                return true;
            }
        }
        return false;
    }
}
