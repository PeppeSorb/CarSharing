package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.ReviewDTO;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = modelMapper.map(reviewDTO, ReviewEntity.class);
        ReviewEntity reviewEntity1 = reviewService.saveReview(reviewEntity);
        ReviewDTO reviewDTO1 = modelMapper.map(reviewEntity1, ReviewDTO.class);
        return reviewDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<ReviewDTO> getAllReview() {
        List<ReviewEntity> reviewEntities = reviewService.getAllReview();
        List<ReviewDTO> reviewDTO = reviewEntities.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
        return reviewDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReviewDTO getReviewById(@PathVariable Long id) {
        ReviewEntity reviewEntity = reviewService.findById(id);
        ReviewDTO reviewDTO = modelMapper.map(reviewEntity, ReviewDTO.class);
        return reviewDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewService.updateReview(id, reviewDTO);

        ReviewDTO reviewDTO1 = modelMapper.map(reviewEntity, ReviewDTO.class);

        return reviewDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        System.out.println("Review " + id + " deleted");
    }
}
