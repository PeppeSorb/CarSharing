package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/vehicle")
@CrossOrigin(origins = "*")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    private ModelMapper modelMapper;
    @Autowired
    public VehicleController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicleDTO){
        VehicleEntity vehicleEntity = modelMapper.map(vehicleDTO, VehicleEntity.class);
        VehicleEntity vehicleEntity1 = vehicleService.saveVehicle(vehicleEntity);
        VehicleDTO vehicleDTO1 = modelMapper.map(vehicleEntity1, VehicleDTO.class);
        return vehicleDTO1;
    }

    @GetMapping("")
    public List<VehicleDTO>getAllVehicle(){
        List<VehicleEntity> vehicleEntities=vehicleService.getAllVehicle();
        List<VehicleDTO> vehicleDTO = vehicleEntities.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
        return vehicleDTO;
    }

    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable Long id){
        VehicleEntity vehicleEntity = vehicleService.findById(id);
        VehicleDTO vehicleDTO = modelMapper.map(vehicleEntity, VehicleDTO.class);
        return vehicleDTO;
    }

    @PutMapping("/{id}")
    public VehicleDTO updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO){

        VehicleEntity vehicleEntity=vehicleService.updateVehicle(id, vehicleDTO);

        VehicleDTO vehicleDTO1 = modelMapper.map(vehicleEntity, VehicleDTO.class);

        return vehicleDTO1;
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return "Vehicle " + id + " deleted";
    }
}

