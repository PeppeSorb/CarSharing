package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.ReviewDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.mapper.ReviewMapper;
import com.stefanogiuseppe.carsharing.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Reviews Controller", description = "This controller allows create, read, update and delete operations on Reviews")
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
    @Operation(description = "Adds a new review to the repository")
    public ReviewDTO addReview(@Parameter(description = "The new review in a JSON format") @RequestBody ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO);
        ReviewEntity reviewEntity1 = reviewService.saveReview(reviewEntity);
        ReviewDTO reviewDTO1 = reviewMapper.toDto(reviewEntity1);
        return reviewDTO1;
    }

    @GetMapping("")
    @ResponseBody
    @Operation(description = "Returns all reviews of the repository")
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
    @Operation(description = "Returns a review inside the repository with the specified id")
    public ReviewDTO getReviewById(@Parameter(description="The id of the requested review") @PathVariable Long id) {
        ReviewEntity reviewEntity = reviewService.findById(id);
        ReviewDTO reviewDTO = reviewMapper.toDto(reviewEntity);
        return reviewDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates some information of an existing review")
    public ReviewDTO updatePatchReview(@Parameter(description="The id of the review to update") @PathVariable Long id, @Parameter(description = "The updated review") @RequestBody ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewService.updateReview(id, reviewDTO);

        ReviewDTO reviewDTO1 = reviewMapper.toDto(reviewEntity);

        return reviewDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates all information of an existing administrator")
    public ReviewDTO updatePutReview(@Parameter(description="The id of the review to update") @PathVariable Long id, @Parameter(description = "The updated information of review") @RequestBody ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewService.updateReview(id, reviewDTO);

        ReviewDTO reviewDTO1 = reviewMapper.toDto(reviewEntity);

        return reviewDTO1;
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "Deletes a review from the repository")
    public void deleteReview(@Parameter(description = "The id of the review to delete") @PathVariable Long id) {
        reviewService.deleteReview(id);
        System.out.println("Review " + id + " deleted");
    }
}
