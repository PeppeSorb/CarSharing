package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.RentalPriceResponse;
import com.stefanogiuseppe.carsharing.dto.RentalDTO;
import com.stefanogiuseppe.carsharing.entity.CategoryEntity;
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
import java.time.Duration;
import java.time.Instant;
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
    public RentalPriceResponse getRentalPrice(RentalEntity r){
        double rentalPrice = 0.0;
        boolean extraPay = false;
        CategoryEntity ce = r.getIdVehicle().getIdModel().getIdCategory();
        Instant startInstant = r.getDateTimeStartRental().toInstant();
        Instant endInstant = r.getDateTimeEndRental().toInstant();
        Duration duration = Duration.between(startInstant, endInstant);
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        String rentType = r.getTypeRental();
        double totDays = ((days  *1.0) + (hours/24.0) + (minutes/1440.0) + (seconds/86400.0));
        switch(rentType){
            case "Hourly":
                rentalPrice = ce.getHourlyRate() * ((days * 24.0) + (hours * 1.0) + (minutes/60.0) + (seconds / 3600.0));
                break;
            case "Daily":
                rentalPrice = ce.getDailyRate();
                if(totDays > 1){
                    extraPay = true;
                    rentalPrice += ce.getExtraHourlyRate() * ((totDays - 1.0) * 24.0);
                }
                break;
            case "Two Days":
                rentalPrice = ce.getTwoDaysRate();
                if(totDays > 2){
                    extraPay = true;
                    rentalPrice += ce.getExtraHourlyRate() * ((totDays - 2.0) * 24.0);
                }
                break;
            case "Weekly":
                rentalPrice = ce.getWeeklyRate();
                if(totDays > 7){
                    extraPay = true;
                    rentalPrice += ce.getExtraHourlyRate() * ((totDays - 7.0) * 24.0);
                }
                break;
            case "Monthly":
                rentalPrice = ce.getMonthlyRate();
                if(totDays > 30){
                    extraPay = true;
                    rentalPrice += ce.getExtraHourlyRate() * ((totDays - 30.0) * 24.0);
                }
                break;
        }
        return new RentalPriceResponse(rentalPrice,extraPay);
    }
    public String payRental(Long idRental){
        Optional<RentalEntity> rOptional = rentalRepository.findById(idRental);
        if(rOptional.isEmpty() == false){
            RentalEntity r = rOptional.get();
            if(r.getPayed() == true){
                return "Rental already payed";
            }else{
                Optional<UserEntity> userOptional = userRepository.findById(r.getIdUser().getId());
                if(userOptional.isEmpty() == false){
                    double price = getRentalPrice(r).getAmount();
                    UserEntity user = userOptional.get();
                    if(user.getResidualCredit() >= price){
                        user.setResidualCredit(user.getResidualCredit() - price);
                        r.setPayed(true);
                        return "Rental payed successfully";
                    }else{
                        return "User credit insufficient";
                    }
                }else{
                    return "User not found";
                }
            }
        }else{
            return "Rental not found";
        }
    }

}
