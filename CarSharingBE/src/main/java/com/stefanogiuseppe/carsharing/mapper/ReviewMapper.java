package com.stefanogiuseppe.carsharing.mapper;

import com.stefanogiuseppe.carsharing.dto.ReviewDTO;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewEntity toEntity(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(reviewDTO.getId());
        reviewEntity.setIdRental(reviewDTO.getIdRental());
        reviewEntity.setDateTimeReview(reviewDTO.getDateTimeReview());
        reviewEntity.setTextReview(reviewDTO.getTextReview());
        reviewEntity.setValutation(reviewDTO.getValutation());
        return reviewEntity;
    }
    public ReviewDTO toDto(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewEntity.getId());
        reviewDTO.setIdRental(reviewEntity.getIdRental());
        reviewDTO.setDateTimeReview(reviewEntity.getDateTimeReview());
        reviewDTO.setTextReview(reviewEntity.getTextReview());
        reviewDTO.setValutation(reviewEntity.getValutation());
        return reviewDTO;
    }
}
