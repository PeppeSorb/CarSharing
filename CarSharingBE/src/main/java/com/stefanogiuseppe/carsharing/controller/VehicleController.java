package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.VehicleMapper;
import com.stefanogiuseppe.carsharing.service.GoogleMapsService;
import com.stefanogiuseppe.carsharing.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Vehicles Controller", description = "This controller allows create, read, update and delete operations on Vehicles")
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
    @Operation(description = "Adds a new vehicle to the repository")
    public VehicleDTO addVehicle(@Parameter(description = "The new administrator in a JSON format") @RequestBody VehicleDTO vehicleDTO){
        VehicleEntity vehicleEntity = vehicleMapper.toEntity(vehicleDTO);
        VehicleEntity vehicleEntity1 = vehicleService.saveVehicle(vehicleEntity);
        VehicleDTO vehicleDTO1 = vehicleMapper.toDto(vehicleEntity1);
        return vehicleDTO1;
    }

    @GetMapping("")
    @Operation(description = "Returns all vehicles of the repository")
    public List<VehicleDTO>getAllVehicle(){
        //List<VehicleEntity> vehicleEntities=vehicleService.getAllVehicle();
        List<VehicleEntity> vehicleEntities = vehicleService.getAllVehicleIsRental();
        List<VehicleDTO> vehicleDTO = vehicleEntities.stream()
                .map(vehicle -> vehicleMapper.toDto(vehicle))
                .collect(Collectors.toList());
        return vehicleDTO;
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns a vehicle inside the repository with the specified id")
    public VehicleDTO getVehicleById(@Parameter(description="The id of the requested vehicle") @PathVariable Long id){
        VehicleEntity vehicleEntity = vehicleService.findById(id);
        VehicleDTO vehicleDTO = vehicleMapper.toDto(vehicleEntity);
        return vehicleDTO;
    }

    @PutMapping("/{id}")
    @Operation(description = "Updates all information of an existing vehicle")
    public VehicleDTO updatePutVehicle(@Parameter(description="The id of the vehicle to update") @PathVariable Long id, @Parameter(description = "The updated vehicle") @RequestBody VehicleDTO vehicleDTO){

        VehicleEntity vehicleEntity=vehicleService.updateVehicle(id, vehicleDTO);

        VehicleDTO vehicleDTO1 = vehicleMapper.toDto(vehicleEntity);

        return vehicleDTO1;
    }
    @PatchMapping("/{id}")
    @Operation(description = "Updates some information of an existing vehicle")
    public VehicleDTO updatePatchVehicle(@Parameter(description="The id of the vehicle to update") @PathVariable Long id, @Parameter(description = "The updated information of vehicle") @RequestBody VehicleDTO vehicleDTO){

        VehicleEntity vehicleEntity=vehicleService.updateVehicle(id, vehicleDTO);

        VehicleDTO vehicleDTO1 = vehicleMapper.toDto(vehicleEntity);

        return vehicleDTO1;
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletes a vehicle from the repository")
    public String deleteVehicle(@Parameter(description = "The id of the vehicle to delete") @PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return "Vehicle " + id + " deleted";
    }
    @GetMapping("/score/{idVehicle}")
    @Operation(description = "Returns the overall score of the reviews about this vehicle")
    public Double getVehicleReviewsScore(@Parameter(description = "The id of the vehicle") @PathVariable Long idVehicle){
        return vehicleService.getVehicleReviewsScore(idVehicle);
    }

    @GetMapping("is_booked/{idVehicle}")
    @Operation(description = "Returns true if the vehicle is already booked, false otherwise")
    public Boolean isBooked(@Parameter(description = "The id of the vehicle to check") @PathVariable Long idVehicle){
        return vehicleService.isBooked(idVehicle);
    }
}

