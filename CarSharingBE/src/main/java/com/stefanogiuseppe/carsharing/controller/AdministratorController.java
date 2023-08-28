package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.AdministratorDTO;
import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.RentalMapper;
import com.stefanogiuseppe.carsharing.service.AdministratorService;

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
@Tag(name = "Administrators Controller", description = "This controller allows create, read, update and delete operations on Administrators")
@RequestMapping(path = "/api/administrator")
@CrossOrigin(origins = "*")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    /*
    private ModelMapper administratorMapper;
    @Autowired
    public AdministratorController(ModelMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
    }
    */
    @Autowired
    private AdministratorMapper administratorMapper;
    @PostMapping("")
    @Operation(description = "Adds a new administrator to the repository")
    public  AdministratorDTO addAdministrator(@Parameter(description = "The new administrator in a JSON format") @RequestBody AdministratorDTO administratorDTO){
        AdministratorEntity administratorEntity = administratorMapper.toEntity(administratorDTO);
        AdministratorEntity administratorEntity1 = administratorService.saveAdministrator(administratorEntity);
        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity1);
        return administratorDTO1;
    }

    @GetMapping("")
    @Operation(description = "Returns all administrators of the repository")
    public List<AdministratorDTO> getAllAdministrator(){
        List<AdministratorEntity> administratorEntities=administratorService.getAllAdministrator();
        List<AdministratorDTO> administratorDTO = administratorEntities.stream()
                .map(administrator -> administratorMapper.toDto(administrator))
                .collect(Collectors.toList());
        return administratorDTO;
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns an administrator inside the repository with the specified id")
    public AdministratorDTO getAdministratorById(@Parameter(description="The id of the requested administrator") @PathVariable Long id){
        AdministratorEntity administratorEntity = administratorService.findById(id);
        AdministratorDTO administratorDTO = administratorMapper.toDto(administratorEntity);
        return administratorDTO;
    }

    @PutMapping("/{id}")
    @Operation(description = "Updates all information of an existing administrator")
    public AdministratorDTO updatePutAdministrator(@Parameter(description="The id of the administrator to update") @PathVariable Long id, @Parameter(description = "The updated administrator") @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity);

        return administratorDTO1;
    }
    @PatchMapping("/{id}")
    @Operation(description = "Updates some information of an existing administrator")
    public AdministratorDTO updatePatchAdministrator(@Parameter(description="The id of the administrator to update") @PathVariable Long id, @Parameter(description = "The updated information of administrator") @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity);

        return administratorDTO1;
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletes an administrator from the repository")
    public String deleteAdministrator(@Parameter(description = "The id of the administrator to delete") @PathVariable Long id){
        administratorService.deleteAdministrator(id);
        return "Administrator " + id + " deleted";
    }
}

