package com.stefanogiuseppe.carsharing.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.stefanogiuseppe.carsharing.config.GoogleMapsProperties;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
    private final GoogleMapsProperties googleMapsProperties;

    public GoogleMapsService(GoogleMapsProperties googleMapsProperties) {
        this.googleMapsProperties = googleMapsProperties;
    }

    public LatLng getCoordinatesFromAddress(String address) {
        String apiKey = googleMapsProperties.getApiKey();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, address).awaitIgnoreError();
        if (results.length > 0) {
            return results[0].geometry.location;
        }
        return null;
    }

    protected LatLng getCoordinatesFromAddress(String country, String address, String city, String houseNumber) {
        String apiKey = googleMapsProperties.getApiKey();
        System.out.println(apiKey);
        try {
            String fullAddress = address + houseNumber + ", " + city + ", " + country;
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();
            GeocodingResult[] results = GeocodingApi.geocode(context, fullAddress).await();
            System.out.println("au");
            if (results.length > 0) {
                System.out.println(results[0].geometry.location);
                return results[0].geometry.location;
            }
        } catch (Exception e) {
            System.out.println("Errore durante la chiamata alle API di Geocoding: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
