package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.RentalPriceResponse;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Rentals Controller", description = "This controller allows create, read, update and delete operations on Rentals")
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

    @PostMapping("") //NUOVO POST -> GETRENTAL SU ENDPOINT "CREATE/VEHICLE/USER
    @ResponseBody
    @Operation(description = "Adds a new rental to the repository")
    public RentalDTO addRental(@Parameter(description = "The new rental in a JSON format") @RequestBody RentalDTO rentalDTO) {
        RentalEntity rentalEntity = rentalMapper.toEntity(rentalDTO);
        RentalEntity rentalEntity1 = rentalService.saveRental(rentalEntity);
        RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity1);
        return rentalDTO1;
    }

    @GetMapping("")
    @ResponseBody
    @Operation(description = "Returns all rentals of the repository")
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
    @Operation(description = "Returns a rental inside the repository with the specified id")
    public RentalDTO getRentalById(@Parameter(description = "The id of the requested rental") @PathVariable Long id) {
        RentalEntity rentalEntity = rentalService.findById(id);
        RentalDTO rentalDTO = rentalMapper.toDto(rentalEntity);
        return rentalDTO;
    }

    @PatchMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates some information of an existing rental")
    public RentalDTO updatePatchRental(@Parameter(description = "The id of the rental to update") @PathVariable Long id, @Parameter(description = "The updated rental") @RequestBody RentalDTO rentalDTO) {

        RentalEntity rentalEntity = rentalService.updateRental(id, rentalDTO);

        RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity);

        return rentalDTO1;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "Updates all information of an existing rental")
    public RentalDTO updatePutRental(@Parameter(description = "The id of the rental to update") @PathVariable Long id, @Parameter(description = "The updated information of rental") @RequestBody RentalDTO rentalDTO) {

        RentalEntity rentalEntity = rentalService.updateRental(id, rentalDTO);

        RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity);

        return rentalDTO1;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "Deletes a rental from the repository")
    public void deleteRental(@Parameter(description = "The id of the rental to delete") @PathVariable Long id) {
        rentalService.deleteRental(id);
        System.out.println("Rental " + id + " deleted");
    }


    //CONTROLLO PRIMA DEL RENTAL
    @PostMapping("/create/{idVehicle}/{idUser}")
    @ResponseBody
    @Operation(description = "Adds a new rental if the vehicle is not currently booked and if the user is not currently renting a vehicle")
    public String getRental(@Parameter(description = "The id of the vehicle to rent") @PathVariable Long idVehicle, @Parameter(description = "The id of the user who wants to rent the vehicle") @PathVariable Long idUser, @Parameter(description = "The rental in a JSON format") @RequestBody RentalDTO rentalDTO) {
        RentalEntity rentalEntityNew = rentalMapper.toEntity(rentalDTO);
        boolean rentalEntity = rentalService.makeReservation(idVehicle);
        boolean userEntity = rentalService.userCanRental(idUser);
        if (rentalEntity && userEntity) {
            RentalEntity rentalEntity1 = rentalService.saveNewRentalByUserAndVehicle(idUser, idVehicle, rentalEntityNew);
            RentalDTO rentalDTO1 = rentalMapper.toDto(rentalEntity1);
            addRental(rentalDTO1);
            return ("Prenotazione effettuata con successo.");
        } else if (rentalEntity == false)
            return ("Il veicolo è già stato prenotato.");
        else if (userEntity == false)
            return ("Non puoi fare altre prenotazioni.");

        return ("Err");
    }

    @GetMapping("/end/{idRental}/{lat}/{lon}")
    @ResponseBody
    @Operation(description = "Ends the specified rental. Requires the user to specify where is the vehicle at the end of the rental.")
    public RentalDTO endRental(@Parameter(description = "The id of the rent") @PathVariable Long idRental, @Parameter(description = "The latitude of vehicle coordinate") @PathVariable double lat, @Parameter(description = "The longitude of vehicle coordinate") @PathVariable double lon) {
        RentalEntity rentalEntity = rentalService.endRental(idRental, lat, lon);
        RentalDTO rentalDTO = rentalMapper.toDto(rentalEntity);
        return rentalDTO;
    }

    @GetMapping("/price/{idRental}")
    @ResponseBody
    @Operation(description = "Given a rental, returns the amount that the user has to pay. If the user returns the vehicle late, extraPay is true and and additional fee is automatically added")
    public RentalPriceResponse rentalPriceTest(@Parameter(description = "The id of the rental") @PathVariable Long idRental) {
        RentalEntity r = rentalService.findById(idRental);
        if (r != null) {
            return rentalService.getRentalPrice(r);
        } else {
            return null;
        }
    }

    @GetMapping("/pay/{idRental}")
    @ResponseBody
    @Operation(description = "Pays the rental for the user, if the user has enough money and the rental hasn't been payed yet.")
    public String payRental(@Parameter(description = "The id of the rental") @PathVariable Long idRental) {
        System.out.println("You asked to pay for rental with id " + idRental);
        return rentalService.payRental(idRental);
    }
}
