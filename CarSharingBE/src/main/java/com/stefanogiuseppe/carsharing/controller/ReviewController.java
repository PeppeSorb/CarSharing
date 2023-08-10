package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.ReviewDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.ReviewMapper;
import com.stefanogiuseppe.carsharing.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
   /* private ModelMapper modelMapper;

    @Autowired
    public ReviewController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }*/

    @Autowired
    private ReviewMapper reviewMapper;

    @PostMapping("")
    @ResponseBody
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO);
        ReviewEntity reviewEntity1 = reviewService.saveReview(reviewEntity);
        ReviewDTO reviewDTO1 = reviewMapper.toDto(reviewEntity1);
        return reviewDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<ReviewDTO> getAllReview() {
        List<ReviewEntity> reviewEntities = reviewService.getAllReview();
        List<ReviewDTO> reviewDTO = new ArrayList<>();

        for (ReviewEntity  r: reviewEntities) {
            ReviewDTO reviewDTO1 = reviewMapper.toDto(r);
            reviewDTO.add(reviewDTO1);
        }
        return reviewDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReviewDTO getReviewById(@PathVariable Long id) {
        ReviewEntity reviewEntity = reviewService.findById(id);
        ReviewDTO reviewDTO = reviewMapper.toDto(reviewEntity);
        return reviewDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ReviewDTO updatePatchReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewService.updateReview(id, reviewDTO);

        ReviewDTO reviewDTO1 = reviewMapper.toDto(reviewEntity);

        return reviewDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ReviewDTO updatePutReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewService.updateReview(id, reviewDTO);

        ReviewDTO reviewDTO1 = reviewMapper.toDto(reviewEntity);

        return reviewDTO1;
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        System.out.println("Review " + id + " deleted");
    }
}
