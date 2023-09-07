package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.ModelMapper;
import com.stefanogiuseppe.carsharing.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Vehicles Models Controller", description = "This controller allows create, read, update and delete operations on Vehicles Models")
@RequestMapping(path = "/api/model")
@CrossOrigin(origins = "*")
public class ModelController {
    @Autowired
    private ModelService modelService;



    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("")
    @Operation(description = "Adds a new vehicle model to the repository")
    public ModelDTO addModel(@Parameter(description = "The new administrator in a JSON format") @RequestBody ModelDTO modelDTO){
        ModelEntity modelEntity = modelMapper.toEntity(modelDTO);
        ModelEntity modelEntity1 = modelService.saveModel(modelEntity);
        ModelDTO modelDTO1 = modelMapper.toDto(modelEntity1);
        return modelDTO1;
    }

    @GetMapping("")
    @Operation(description = "Returns all vehicles models of the repository")
    public List<ModelDTO>getAllModel(){
        List<ModelEntity> modelEntities=modelService.getAllModel();
        List<ModelDTO> modelDTO = modelEntities.stream()
                .map(model -> modelMapper.toDto(model))
                .collect(Collectors.toList());
        return modelDTO;
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns a model inside the repository with the specified id")
    public ModelDTO getModelById(@Parameter(description="The id of the requested model") @PathVariable Long id){
        ModelEntity modelEntity = modelService.findById(id);
        ModelDTO modelDTO = modelMapper.toDto(modelEntity);
        return modelDTO;
    }

    @PutMapping("/{id}")
    @Operation(description = "Updates all information of an existing vehicle model")
    public ModelDTO updatePutModel(@Parameter(description="The id of the vehicle model to update") @PathVariable Long id, @Parameter(description = "The updated vehicle model") @RequestBody ModelDTO modelDTO){

        ModelEntity modelEntity=modelService.updateModel(id, modelDTO);

        ModelDTO modelDTO1 = modelMapper.toDto(modelEntity);

        return modelDTO1;
    }
    @PatchMapping("/{id}")
    @Operation(description = "Updates some information of an existing vehicle model")
    public ModelDTO updatePatchModel(@Parameter(description="The id of the vehicle model to update") @PathVariable Long id, @Parameter(description = "The updated information of vehicle model") @RequestBody ModelDTO modelDTO){

        ModelEntity modelEntity=modelService.updateModel(id, modelDTO);

        ModelDTO modelDTO1 = modelMapper.toDto(modelEntity);

        return modelDTO1;
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletes a vehicle model from the repository")
    public String deleteModel(@Parameter(description = "The id of the vehicle model to delete") @PathVariable Long id){
        modelService.deleteModel(id);
        return "Model " + id + " deleted";
    }
    //TODO: scrivere il metodo uploadImageToModel
    @PostMapping(value = "/upload-image/{modelId}", consumes = {"multipart/form-data"})
    @Operation(description = "Uploads an image to the server for a vehicle model")
    public String uploadImageToModel(@Parameter(description = "The id of the model to update") @PathVariable Long modelId, @Parameter(description = "The image file to upload") @RequestParam("file") MultipartFile file) {
        return modelService.uploadImageToModel(modelId,file);
    }
    @DeleteMapping(value = "/delete-unused")
    @Operation(description = "Deletes image files inside the models images folder that are not used by any vehicle model.")
    public void deleteUnusedModelImages(){
        modelService.deleteUnusedModelImages();
    }

    @GetMapping("/score/{idModel}")
    @Operation(description = "Returns the overall score of the reviews about this model")
    public Double getModelReviewsScore(@Parameter(description = "The id of the model") @PathVariable Long idModel){
        return modelService.getModelReviewsScore(idModel);
    }

    @GetMapping("is_booked/{idModel}")
    @Operation(description = "Returns true if a vehicle of this model is already booked, false otherwise")
    public Boolean isBooked(@Parameter(description = "The id of the model to check") @PathVariable Long idModel){
        return modelService.isBooked(idModel);
    }
}

