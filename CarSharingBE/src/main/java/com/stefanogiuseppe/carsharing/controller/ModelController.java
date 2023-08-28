package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.ModelMapper;
import com.stefanogiuseppe.carsharing.service.ModelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Vehicles Models Controller", description = "This controller allows create, read, update and delete operations on Vehicles Models")
@RequestMapping(path = "/api/model")
@CrossOrigin(origins = "*")
public class ModelController {
    @Autowired
    private ModelService modelService;
    /*
    private ModelMapper modelMapper;
    @Autowired
    public ModelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    */
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("")
    public ModelDTO addModel(@RequestBody ModelDTO modelDTO){
        ModelEntity modelEntity = modelMapper.toEntity(modelDTO);
        ModelEntity modelEntity1 = modelService.saveModel(modelEntity);
        ModelDTO modelDTO1 = modelMapper.toDto(modelEntity1);
        return modelDTO1;
    }

    @GetMapping("")
    public List<ModelDTO>getAllModel(){
        List<ModelEntity> modelEntities=modelService.getAllModel();
        List<ModelDTO> modelDTO = modelEntities.stream()
                .map(model -> modelMapper.toDto(model))
                .collect(Collectors.toList());
        return modelDTO;
    }

    @GetMapping("/{id}")
    public ModelDTO getModelById(@PathVariable Long id){
        ModelEntity modelEntity = modelService.findById(id);
        ModelDTO modelDTO = modelMapper.toDto(modelEntity);
        return modelDTO;
    }

    @PutMapping("/{id}")
    public ModelDTO updatePutModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO){

        ModelEntity modelEntity=modelService.updateModel(id, modelDTO);

        ModelDTO modelDTO1 = modelMapper.toDto(modelEntity);

        return modelDTO1;
    }
    @PatchMapping("/{id}")
    public ModelDTO updatePatchModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO){

        ModelEntity modelEntity=modelService.updateModel(id, modelDTO);

        ModelDTO modelDTO1 = modelMapper.toDto(modelEntity);

        return modelDTO1;
    }

    @DeleteMapping("/{id}")
    public String deleteModel(@PathVariable Long id){
        modelService.deleteModel(id);
        return "Model " + id + " deleted";
    }
}

