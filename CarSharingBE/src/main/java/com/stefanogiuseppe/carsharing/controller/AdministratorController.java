package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.AdministratorDTO;
import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.mapper.AdministratorMapper;
import com.stefanogiuseppe.carsharing.mapper.RentalMapper;
import com.stefanogiuseppe.carsharing.service.AdministratorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
    public  AdministratorDTO addAdministrator(@RequestBody AdministratorDTO administratorDTO){
        AdministratorEntity administratorEntity = administratorMapper.toEntity(administratorDTO);
        AdministratorEntity administratorEntity1 = administratorService.saveAdministrator(administratorEntity);
        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity1);
        return administratorDTO1;
    }

    @GetMapping("")
    public List<AdministratorDTO> getAllAdministrator(){
        List<AdministratorEntity> administratorEntities=administratorService.getAllAdministrator();
        List<AdministratorDTO> administratorDTO = administratorEntities.stream()
                .map(administrator -> administratorMapper.toDto(administrator))
                .collect(Collectors.toList());
        return administratorDTO;
    }

    @GetMapping("/{id}")
    public AdministratorDTO getAdministratorById(@PathVariable Long id){
        AdministratorEntity administratorEntity = administratorService.findById(id);
        AdministratorDTO administratorDTO = administratorMapper.toDto(administratorEntity);
        return administratorDTO;
    }

    @PutMapping("/{id}")
    public AdministratorDTO updatePutAdministrator(@PathVariable Long id, @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity);

        return administratorDTO1;
    }
    @PatchMapping("/{id}")
    public AdministratorDTO updatePatchAdministrator(@PathVariable Long id, @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.toDto(administratorEntity);

        return administratorDTO1;
    }

    @DeleteMapping("/{id}")
    public String deleteAdministrator(@PathVariable Long id){
        administratorService.deleteAdministrator(id);
        return "Administrator " + id + " deleted";
    }
}

