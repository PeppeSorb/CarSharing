package com.stefanogiuseppe.carsharing.service;
import com.google.maps.model.LatLng;
import com.stefanogiuseppe.carsharing.dto.VehicleDTO;
import com.stefanogiuseppe.carsharing.entity.RentalEntity;
import com.stefanogiuseppe.carsharing.entity.ReviewEntity;
import com.stefanogiuseppe.carsharing.entity.VehicleEntity;
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
import java.util.stream.Stream;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private GeoCodingService geoCodingService;

    @Autowired
    private ReviewService reviewService;
    //private GoogleMapsService googleMapsService;

    public VehicleEntity saveVehicle(VehicleEntity vehicleEntity) {
        return vehicleRepository.save(vehicleEntity);
    }

    public List<VehicleEntity> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    public VehicleEntity findById(Long id) {
        List<VehicleEntity> list= getAllVehicleIsRental();
        return vehicleRepository.findById(id).orElseThrow();
    }

    public VehicleEntity updateVehicle(Long id, VehicleDTO vehicleDTO) {
            VehicleEntity vehicleEntity = findById(id);
            vehicleDTO.setId(vehicleEntity.getId());
            vehicleEntity.setIdModel(vehicleDTO.getIdMod());

            BeanUtils.copyProperties(vehicleDTO, vehicleEntity, getNullPropertyNames(vehicleDTO));
            return vehicleRepository.save(vehicleEntity);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteVehicle(Long id) {
            VehicleEntity vehicleEntity = vehicleRepository.findById(id).orElseThrow();
            vehicleEntity.setDeleted(true);
            vehicleRepository.save(vehicleEntity);
    }

    public Boolean isBooked(Long idVehicle) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(idVehicle).orElseThrow();
        // Verifica se l'ID del veicolo è già presente in rental
        boolean isRental = false;
        List<RentalEntity> rentals = vehicleEntity.getRentals();
        for (RentalEntity rental : rentals) {
            if (rental.getDateTimeStartRental().before(new Date()) && rental.getDateTimeEndRental() == null) {
                isRental = true;
            } else if (rental.getDateTimeStartRental().before(new Date()) && rental.getDateTimeEndRental().after(new Date())) {
                isRental = true;
            }


            // System.out.println(emptyRental);
        }
        return isRental;
    }

    public List<VehicleEntity> getAllVehicleIsRental() {
        List<VehicleEntity> vehicles = vehicleRepository.findAll();
        List<VehicleEntity> newVehicles = new ArrayList<>();
        for(VehicleEntity vehicle: vehicles) {
            List<Double> ls= geoCodingService.getCoordinatesFromAddress(vehicle.getCountry(), vehicle.getStreet(), vehicle.getCity(), vehicle.getHouseNumber());
            //LatLng coordinates = googleMapsService.getCoordinatesFromAddress(vehicle.getCountry(), vehicle.getStreet(), vehicle.getCity(), vehicle.getHouseNumber());
            if(ls!=null){
            vehicle.setLatitude(ls.get(0));
            vehicle.setLongitude(ls.get(1));
            }



            Boolean isRental = isBooked(vehicle.getId());
            vehicle.setBooked(isRental);


            saveVehicle(vehicle);
            System.out.println(isRental);
            newVehicles.add(vehicle);
        }
        return newVehicles;
    }
    public Double getVehicleReviewsScore(Long idVehicle){
        List<ReviewEntity> reviews = reviewService.getAllReview();
        double sum = 0.0;
        int count = 0;
        for(ReviewEntity review : reviews){
            if(review.getIdRental().getIdVehicle().getId() == idVehicle){
                //la recensione riguarda questo veicolo
                sum = sum + review.getValutation();
                count++;
            }
        }
        return sum/(count+0.0);
    }


}