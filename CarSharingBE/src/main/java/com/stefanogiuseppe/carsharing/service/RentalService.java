package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.UserEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
import com.stefanogiuseppe.carsharing.repository.RentalRepository;
import com.stefanogiuseppe.carsharing.repository.UserRepository;
import com.stefanogiuseppe.carsharing.repository.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GeoCodingService geoCodingService;

    public RentalEntity saveRental(RentalEntity rentalEntity) {
        rentalEntity.setDeleted(false);
        return rentalRepository.save(rentalEntity);
    }

    public List<RentalEntity> getAllRental() {
        return rentalRepository.findAll();
    }

    public RentalEntity findById(Long id) {
        return rentalRepository.findById(id).orElseThrow();
    }

    public RentalEntity updateRental(Long id, RentalDTO rentalDTO) {
        RentalEntity rentalEntity = findById(id);
        rentalDTO.setId(rentalEntity.getId());

        BeanUtils.copyProperties(rentalDTO, rentalEntity, getNullPropertyNames(rentalDTO));
        return rentalRepository.save(rentalEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteRental(Long id) {
        RentalEntity rentalEntity = rentalRepository.findById(id).orElseThrow();
        rentalEntity.setDeleted(true);
        rentalRepository.save(rentalEntity);
    }

    public boolean makeReservation(Long idVehicle) {
        VehicleEntity vehicleEntity= vehicleRepository.findById(idVehicle).orElseThrow();
        // Verifica se l'ID del veicolo è già presente in rental
        boolean isVehicleAlreadyRented = rentalRepository.existsByIdVehicle(vehicleEntity);
        boolean emptyRental=true; //controllo se comunque tra le prenotazioni le date sono scadute
        List<RentalEntity> rentals = vehicleEntity.getRentals();
        for(RentalEntity rental:rentals){
            if(rental.getDateTimeStartRental().before(new Date()) && rental.getDateTimeEndRental()==null) {
                emptyRental = false;
            }
            else if(rental.getDateTimeStartRental().before(new Date()) && rental.getDateTimeEndRental().after(new Date())) {
                emptyRental = false;
            }

           // System.out.println(emptyRental);
        }


        if (isVehicleAlreadyRented && (emptyRental==false)) {
            return false;//("Il veicolo è già stato prenotato.");
        }
        else{
            //AGGIUNGERE UN NUOVO RENTAL
            return true;//("Prenotazione effettuata con successo.");
        }

    }

    public boolean userCanRental(Long idUser){
        UserEntity userEntity= userRepository.findById(idUser).orElseThrow();
        boolean isUserValid=rentalRepository.existsByIdUser(userEntity);
        boolean isUserCanRental=true;

        List<RentalEntity> rentals = userEntity.getRentals();

        for(RentalEntity rental:rentals){
            if(rental.getDateTimeStartRental().before(new Date()) && rental.getDateTimeEndRental()==null) {
                isUserCanRental = false;
            }
            else if(rental.getDateTimeStartRental().before(new Date()) && rental.getDateTimeEndRental().after(new Date())) {
                isUserCanRental = false;
            }

            System.out.println(isUserCanRental);
        }

        if (isUserValid && (isUserCanRental==false)) {
            return false;//("Non puoi fare altre prenotazioni.");
        }
        else{
            //AGGIUNGERE UN NUOVO RENTAL
            return true;//("Prenotazione effettuata con successo.");
        }
    }

    public RentalEntity saveNewRentalByUserAndVehicle(Long idUser, Long idVehicle, RentalEntity rentalEntity){
        UserEntity userEntity= userRepository.findById(idUser).orElseThrow();
        VehicleEntity vehicleEntity = vehicleRepository.findById(idVehicle).orElseThrow();

        rentalEntity.setIdVehicle(vehicleEntity);
        rentalEntity.setIdUser(userEntity);
        return rentalEntity;

    }
    public RentalEntity endRental(Long idRental, double lat, double lon){
        RentalEntity rentalToEnd = rentalRepository.findById(idRental).orElseThrow();
        rentalToEnd.setDateTimeEndRental(new Date());
        Long vehicleId = rentalToEnd.getIdVehicle().getId();
        Optional<VehicleEntity> vehicleToMoveOptional = vehicleRepository.findById(vehicleId);
        //se esiste un veicolo associato al noleggio...
        if(!vehicleToMoveOptional.isEmpty()){
            VehicleEntity vehicleToMove = vehicleToMoveOptional.get();
            //aggiorna la posizione del veicolo
            vehicleToMove.setLatitude(lat);
            vehicleToMove.setLongitude(lon);
            List<String> address = geoCodingService.getAddressFromCoordinates(lat,lon);
            vehicleToMove.setCountry(address.get(0));
            vehicleToMove.setStreet(address.get(1));
            vehicleToMove.setCity(address.get(2));
            vehicleToMove.setHouseNumber(address.get(3));
            //sblocca il veicolo
            vehicleToMove.setBooked(false);
            vehicleRepository.save(vehicleToMove);
        }
        return rentalRepository.save(rentalToEnd);
    }

}
