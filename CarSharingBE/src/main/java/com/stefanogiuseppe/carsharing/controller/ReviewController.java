package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RechargeDTO;
import com.stefanogiuseppe.carsharing.dto.ReviewDTO;
import com.stefanogiuseppe.carsharing.entity.RechargeEntity;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.service.RechargeService;
import com.stefanogiuseppe.carsharing.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = modelMapper.map(reviewDTO, ReviewEntity.class);
        ReviewEntity reviewEntity1 = reviewService.saveReview(reviewEntity);
        ReviewDTO reviewDTO1 = modelMapper.map(reviewEntity1, ReviewDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<ReviewDTO>> getAllReview() {
        List<ReviewEntity> reviewEntities = reviewService.getAllReview();
        List<ReviewDTO> reviewDTO = reviewEntities.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewEntity reviewEntity = reviewService.findById(id);
        ReviewDTO reviewDTO = modelMapper.map(reviewEntity, ReviewDTO.class);
        return ResponseEntity.ok(reviewDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewService.updateReview(id, reviewDTO);

        ReviewDTO reviewDTO1 = modelMapper.map(reviewEntity, ReviewDTO.class);

        return ResponseEntity.ok(reviewDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review " + id + " deleted");
    }
}
