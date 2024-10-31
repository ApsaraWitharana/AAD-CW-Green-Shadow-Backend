package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.VehicleDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.VehicleNotFound;
import lk.ijse.gdse68.greenshadowbackend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;

    //TODO: Vehicle CRUD Implement

    //TODO: Save vehicle
    @PostMapping
    public ResponseEntity<String> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        try {
            System.out.println("Saving vehicle: " + vehicleDTO);
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>("Vehicle saved successfully.", HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>("Vehicle data could not be saved, data persistence failed.",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error occurred while saving the vehicle.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/{vehicleCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> updateVehicle(@PathVariable ("vehicleCode") String vehicleCode,@RequestBody VehicleDTO vehicleDTO){
        try {
            vehicleService.updateVehicle(vehicleCode,vehicleDTO);
            return new ResponseEntity<>("Vehicle details Update Successfully!!",HttpStatus.OK);

        }catch (VehicleNotFound e){
            return new ResponseEntity<>("Vehicle not found!!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
