package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.VehicleMapper;
import com.stefanogiuseppe.carsharing.service.VehicleService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Vehicles Controller", description = "This controller allows create, read, update and delete operations on Vehicles")
@RequestMapping(path = "/api/vehicle")
@CrossOrigin(origins = "*")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    /*
    private ModelMapper modelMapper;
    @Autowired
    public VehicleController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    */
    @Autowired
    private VehicleMapper vehicleMapper;
    @PostMapping("")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicleDTO){
        VehicleEntity vehicleEntity = vehicleMapper.toEntity(vehicleDTO);
        VehicleEntity vehicleEntity1 = vehicleService.saveVehicle(vehicleEntity);
        VehicleDTO vehicleDTO1 = vehicleMapper.toDto(vehicleEntity1);
        return vehicleDTO1;
    }

    @GetMapping("")
    public List<VehicleDTO>getAllVehicle(){
        List<VehicleEntity> vehicleEntities=vehicleService.getAllVehicle();
        List<VehicleDTO> vehicleDTO = vehicleEntities.stream()
                .map(vehicle -> vehicleMapper.toDto(vehicle))
                .collect(Collectors.toList());
        return vehicleDTO;
    }

    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable Long id){
        VehicleEntity vehicleEntity = vehicleService.findById(id);
        VehicleDTO vehicleDTO = vehicleMapper.toDto(vehicleEntity);
        return vehicleDTO;
    }

    @PutMapping("/{id}")
    public VehicleDTO updatePutVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO){

        VehicleEntity vehicleEntity=vehicleService.updateVehicle(id, vehicleDTO);

        VehicleDTO vehicleDTO1 = vehicleMapper.toDto(vehicleEntity);

        return vehicleDTO1;
    }
    @PatchMapping("/{id}")
    public VehicleDTO updatePatchVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO){

        VehicleEntity vehicleEntity=vehicleService.updateVehicle(id, vehicleDTO);

        VehicleDTO vehicleDTO1 = vehicleMapper.toDto(vehicleEntity);

        return vehicleDTO1;
    }

    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return "Vehicle " + id + " deleted";
    }
}

