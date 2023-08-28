package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.AdministratorDTO;
import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.RentalMapper;
import com.stefanogiuseppe.carsharing.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Administrators Controller", description = "This controller allows create, read, update and delete operations on Administrators")
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
    @ApiOperation(value = "Adds a new administrator to the repository", response = AdministratorDTO.class)
    public  AdministratorDTO addAdministrator(@ApiParam(value = "The new administrator in a JSON format") @RequestBody AdministratorDTO administratorDTO){
        AdministratorEntity administratorEntity = administratorMapper.toEntity(administratorDTO);
        AdministratorEntity administratorEntity1 = administratorService.saveAdministrator(administratorEntity);
        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity1);
        return administratorDTO1;
    }

    @GetMapping("")
    @ApiOperation(value = "Returns all administrators of the repository", response = List.class)
    public List<AdministratorDTO> getAllAdministrator(){
        List<AdministratorEntity> administratorEntities=administratorService.getAllAdministrator();
        List<AdministratorDTO> administratorDTO = administratorEntities.stream()
                .map(administrator -> administratorMapper.toDto(administrator))
                .collect(Collectors.toList());
        return administratorDTO;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns an administrator inside the repository with the specified id", response = AdministratorDTO.class)
    public AdministratorDTO getAdministratorById(@ApiParam(value="The id of the requested administrator") @PathVariable Long id){
        AdministratorEntity administratorEntity = administratorService.findById(id);
        AdministratorDTO administratorDTO = administratorMapper.toDto(administratorEntity);
        return administratorDTO;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates all information of an existing administrator", response = AdministratorDTO.class)
    public AdministratorDTO updatePutAdministrator(@ApiParam(value="The id of the administrator to update") @PathVariable Long id, @ApiParam("The updated administrator") @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity);

        return administratorDTO1;
    }
    @PatchMapping("/{id}")
    @ApiOperation(value = "Updates some information of an existing administrator", response = AdministratorDTO.class)
    public AdministratorDTO updatePatchAdministrator(@ApiParam(value="The id of the administrator to update") @PathVariable Long id, @ApiParam("The updated information of administrator") @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity);

        return administratorDTO1;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes an administrator from the repository", response = String.class)
    public String deleteAdministrator(@ApiParam(value = "The id of the administrator to delete") @PathVariable Long id){
        administratorService.deleteAdministrator(id);
        return "Administrator " + id + " deleted";
    }
}

