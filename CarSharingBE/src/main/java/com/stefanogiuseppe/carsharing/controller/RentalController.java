package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.dto.UserDTO;
import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.mapper.RentalMapper;
import com.stefanogiuseppe.carsharing.mapper.VehicleMapper;
import com.stefanogiuseppe.carsharing.service.RentalService;
import com.stefanogiuseppe.carsharing.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/rental")
@CrossOrigin(origins = "*")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Autowired
    private VehicleService vehicleService;
 /*   private ModelMapper modelMapper;

    @Autowired
    public RentalController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }*/

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @PostMapping("")
    @ResponseBody
    public RentalDTO addRental(@RequestBody RentalDTO rentalDTO) {
        RentalEntity rentalEntity = rentalMapper.toEntity(rentalDTO);
        RentalEntity rentalEntity1 = rentalService.saveRental(rentalEntity);
        RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity1);
        return rentalDTO1;
    }

    @GetMapping("")
    @ResponseBody
    public List<RentalDTO> getAllRental() {
        List<RentalEntity> rentalEntities = rentalService.getAllRental();
        List<RentalDTO> rentalDTO = new ArrayList<>();
        for (RentalEntity r : rentalEntities) {
            RentalDTO rentalDTO1 = rentalMapper.toDto(r);
            rentalDTO.add(rentalDTO1);
        }
        return rentalDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RentalDTO getRentalById(@PathVariable Long id) {
        RentalEntity rentalEntity = rentalService.findById(id);
        RentalDTO rentalDTO = rentalMapper.toDto(rentalEntity);
        return rentalDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public RentalDTO updatePatchRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {

        RentalEntity rentalEntity = rentalService.updateRental(id, rentalDTO);

        RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity);

        return rentalDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public RentalDTO updatePutRental(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {

        RentalEntity rentalEntity = rentalService.updateRental(id, rentalDTO);

        RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity);

        return rentalDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        System.out.println("Rental " + id + " deleted");
    }

    @GetMapping("/create/{idVehicle}/{idUser}")
    @ResponseBody
    public String getRental(@PathVariable Long idVehicle, @PathVariable Long idUser, @RequestBody RentalDTO rentalDTO) {
        RentalEntity rentalEntityNew= rentalMapper.toEntity(rentalDTO);
        boolean rentalEntity = rentalService.makeReservation(idVehicle);
        boolean userEntity= rentalService.userCanRental(idUser);
        if(rentalEntity && userEntity){
            RentalEntity rentalEntity1=rentalService.saveNewRentalByUserAndVehicle(idUser, idVehicle, rentalEntityNew);
            RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity1);
            addRental(rentalDTO1);
            return ("Prenotazione effettuata con successo.");
        }
        else if(rentalEntity==false)
            return ("Il veicolo è già stato prenotato.");
        else if(userEntity==false)
            return ("Non puoi fare altre prenotazioni.");

        return ("Err");
    }
}
