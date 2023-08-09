package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.AdministratorDTO;
import com.stefanogiuseppe.carsharing.entity.AdministratorEntity;
import com.stefanogiuseppe.carsharing.service.AdministratorService;
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
    private ModelMapper administratorMapper;
    @Autowired
    public AdministratorController(ModelMapper administratorMapper) {
        this.administratorMapper = administratorMapper;
    }

    @PostMapping("")
    public ResponseEntity<AdministratorDTO>addAdministrator(@RequestBody AdministratorDTO administratorDTO){
        AdministratorEntity administratorEntity = administratorMapper.map(administratorDTO, AdministratorEntity.class);
        AdministratorEntity administratorEntity1 = administratorService.saveAdministrator(administratorEntity);
        AdministratorDTO administratorDTO1 = administratorMapper.map(administratorEntity1, AdministratorDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(administratorDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<AdministratorDTO>>getAllAdministrator(){
        List<AdministratorEntity> administratorEntities=administratorService.getAllAdministrator();
        List<AdministratorDTO> administratorDTO = administratorEntities.stream()
                .map(administrator -> administratorMapper.map(administrator, AdministratorDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(administratorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDTO>getAdministratorById(@PathVariable Long id){
        AdministratorEntity administratorEntity = administratorService.findById(id);
        AdministratorDTO administratorDTO = administratorMapper.map(administratorEntity, AdministratorDTO.class);
        return ResponseEntity.ok(administratorDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdministratorDTO>updateAdministrator(@PathVariable Long id, @RequestBody AdministratorDTO administratorDTO){

        AdministratorEntity administratorEntity=administratorService.updateAdministrator(id, administratorDTO);

        AdministratorDTO administratorDTO1 = administratorMapper.map(administratorEntity, AdministratorDTO.class);

        return ResponseEntity.ok(administratorDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAdministrator(@PathVariable Long id){
        administratorService.deleteAdministrator(id);
        return ResponseEntity.ok("Administrator " + id + " deleted");
    }
}

