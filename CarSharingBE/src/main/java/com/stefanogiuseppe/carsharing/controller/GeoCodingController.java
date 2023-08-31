package com.stefanogiuseppe.carsharing.controller;

import com.stefanogiuseppe.carsharing.service.GeoCodingService;
import com.stefanogiuseppe.carsharing.service.RechargeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "GeoCoding Controller", description = "This controller is necessary to test the geocoding service")
@RequestMapping(path = "/api/geocoding")
@CrossOrigin(origins = "*")
public class GeoCodingController {
    @Autowired
    private GeoCodingService geoCodingService;

    @GetMapping("/{lat}/{lon}")
    @Operation(description = "Given a latitude and a longitude, returns the address.")
    public List<String> testReverseGeoCoding(@Parameter(description="The latitude") @PathVariable double lat, @Parameter(description="The longitude") @PathVariable double lon){
        return geoCodingService.getAddressFromCoordinates(lat,lon);
    }
}
