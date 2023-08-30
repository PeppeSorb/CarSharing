package com.stefanogiuseppe.carsharing.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GeoCodingService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Double> getCoordinatesFromAddress(String country, String address, String city, String houseNumber){
        String apiUrl="https://nominatim.openstreetmap.org/search";
        String fullAddress = address + " " + houseNumber + " " + city + " "+ country;
        String formattedAddress = fullAddress.replace(" ", "+");
        String requestUrl = apiUrl + "?format=json&q=" + formattedAddress;
        System.out.println(formattedAddress);
        System.out.println("REQUEST URL: " + requestUrl);
        // Esegui la richiesta GET
        String response = restTemplate.getForObject(requestUrl, String.class);

        List<Double> ls= new ArrayList<>();
        // Analizza la risposta JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            System.out.println(jsonNode);
            if (jsonNode.isArray() && jsonNode.size() > 0) {
                JsonNode firstResult = jsonNode.get(0);
                ls.add(firstResult.get("lat").asDouble());
                ls.add(firstResult.get("lon").asDouble());
                System.out.println(firstResult.get("lat").asDouble());
                return ls;
                /*double latitude = firstResult.get("lat").asDouble();
                double longitude = firstResult.get("lon").asDouble();
                return new Coordinates(latitude, longitude);*/
            }
        } catch (IOException e) {
            System.out.println("ERR");
            e.printStackTrace();
        }

        return null;
    }
}
