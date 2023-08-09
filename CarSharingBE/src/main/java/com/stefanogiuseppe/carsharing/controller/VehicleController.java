package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.service.VehicleService;
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
    public ResponseEntity<VehicleDTO>addVehicle(@RequestBody VehicleDTO vehicleDTO){
        VehicleEntity vehicleEntity = modelMapper.map(vehicleDTO, VehicleEntity.class);
        VehicleEntity vehicleEntity1 = vehicleService.saveVehicle(vehicleEntity);
        VehicleDTO vehicleDTO1 = modelMapper.map(vehicleEntity1, VehicleDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<VehicleDTO>>getAllVehicle(){
        List<VehicleEntity> vehicleEntities=vehicleService.getAllVehicle();
        List<VehicleDTO> vehicleDTO = vehicleEntities.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(vehicleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO>getVehicleById(@PathVariable Long id){
        VehicleEntity vehicleEntity = vehicleService.findById(id);
        VehicleDTO vehicleDTO = modelMapper.map(vehicleEntity, VehicleDTO.class);
        return ResponseEntity.ok(vehicleDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO>updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO){

        VehicleEntity vehicleEntity=vehicleService.updateVehicle(id, vehicleDTO);

        VehicleDTO vehicleDTO1 = modelMapper.map(vehicleEntity, VehicleDTO.class);

        return ResponseEntity.ok(vehicleDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle " + id + " deleted");
    }
}

