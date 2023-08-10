package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.ModelDTO;
import com.stefanogiuseppe.carsharing.entity.ModelEntity;
import com.stefanogiuseppe.carsharing.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/model")
@CrossOrigin(origins = "*")
public class ModelController {
    @Autowired
    private ModelService modelService;
    private ModelMapper modelMapper;
    @Autowired
    public ModelController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ModelDTO addModel(@RequestBody ModelDTO modelDTO){
        ModelEntity modelEntity = modelMapper.map(modelDTO, ModelEntity.class);
        ModelEntity modelEntity1 = modelService.saveModel(modelEntity);
        ModelDTO modelDTO1 = modelMapper.map(modelEntity1, ModelDTO.class);
        return modelDTO1;
    }

    @GetMapping("")
    public List<ModelDTO>getAllModel(){
        List<ModelEntity> modelEntities=modelService.getAllModel();
        List<ModelDTO> modelDTO = modelEntities.stream()
                .map(model -> modelMapper.map(model, ModelDTO.class))
                .collect(Collectors.toList());
        return modelDTO;
    }

    @GetMapping("/{id}")
    public ModelDTO getModelById(@PathVariable Long id){
        ModelEntity modelEntity = modelService.findById(id);
        ModelDTO modelDTO = modelMapper.map(modelEntity, ModelDTO.class);
        return modelDTO;
    }

    @PutMapping("/{id}")
    public ModelDTO updatePutModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO){

        ModelEntity modelEntity=modelService.updateModel(id, modelDTO);

        ModelDTO modelDTO1 = modelMapper.map(modelEntity, ModelDTO.class);

        return modelDTO1;
    }
    @PatchMapping("/{id}")
    public ModelDTO updatePatchModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO){

        ModelEntity modelEntity=modelService.updateModel(id, modelDTO);

        ModelDTO modelDTO1 = modelMapper.map(modelEntity, ModelDTO.class);

        return modelDTO1;
    }

    @DeleteMapping("/{id}")
    public String deleteModel(@PathVariable Long id){
        modelService.deleteModel(id);
        return "Model " + id + " deleted";
    }
}

