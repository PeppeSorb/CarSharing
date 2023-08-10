package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/rental")
@CrossOrigin(origins = "*")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    private ModelMapper modelMapper;

    @Autowired
    public RentalController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    @ResponseBody
    public RentalDTO addRental(@RequestBody RentalDTO rentalDTO) {
        RentalEntity rentalEntity = modelMapper.map(rentalDTO, RentalEntity.class);
        RentalEntity rentalEntity1 = rentalService.saveRental(rentalEntity);
        RentalDTO rentalDTO1 = modelMapper.map(rentalEntity1, RentalDTO.class);
        return rentalDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<RentalDTO> getAllRental() {
        List<RentalEntity> rentalEntities = rentalService.getAllRental();
        List<RentalDTO> rentalDTO = rentalEntities.stream()
                .map(rental -> modelMapper.map(rental, RentalDTO.class))
                .collect(Collectors.toList());
        return rentalDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RentalDTO getRentalById(@PathVariable Long id) {
        RentalEntity rentalEntity = rentalService.findById(id);
        RentalDTO rentalDTO = modelMapper.map(rentalEntity, RentalDTO.class);
        return rentalDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public RentalDTO updateRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {

        RentalEntity rentalEntity = rentalService.updateRental(id, rentalDTO);

        RentalDTO rentalDTO1 = modelMapper.map(rentalEntity, RentalDTO.class);

        return rentalDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        System.out.println("Rental " + id + " deleted");
    }
}
