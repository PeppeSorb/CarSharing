package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.ReviewDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.repository.ReviewRepository;
import com.stefanogiuseppe.carsharing.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewEntity saveReview(ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }

    public List<ReviewEntity> getAllReview() {
        return reviewRepository.findAll();
    }

    public ReviewEntity findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    public ReviewEntity updateReview(Long id, ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = findById(id);
        reviewDTO.setId(reviewEntity.getId());

        BeanUtils.copyProperties(reviewDTO, reviewEntity, getNullPropertyNames(reviewDTO));
        return reviewRepository.save(reviewEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteReview(Long id) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElseThrow();
        reviewEntity.setDeleted(true);
        reviewRepository.save(reviewEntity);
    }
}
