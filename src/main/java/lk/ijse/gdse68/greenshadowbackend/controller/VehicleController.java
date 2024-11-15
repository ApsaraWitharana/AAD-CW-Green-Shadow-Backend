package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleResponse;
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

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
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
    //TODO: Update
    @PatchMapping(value = "/{vehicleCode}")
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

    //TODO:Delete
    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<String> deleteVehicle(@PathVariable ("vehicleCode") String vehicleCode){
        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>("Vehicle Delete Successfully!!",HttpStatus.OK);
        }catch (VehicleNotFound e){
            return new ResponseEntity<>("Vehicle not found!!",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: GetSelect id
    @GetMapping(value = "/{vehicleCode}")
    public  ResponseEntity<VehicleResponse> getSelectedVehicleId(@PathVariable ("vehicleCode") String vehicleCode){
        VehicleResponse vehicleResponse = vehicleService.getSelectedVehicleId(vehicleCode);
        if (vehicleResponse instanceof VehicleDTO){
            return new ResponseEntity<>(vehicleResponse,HttpStatus.OK); //return NOT_FOUND (204)
        }else if (vehicleResponse instanceof VehicleErrorResponse){
            return new ResponseEntity<>(vehicleResponse,HttpStatus.NOT_FOUND); //return NOT_FOUND (404)
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //return INTERNAL_SERVER_ERROR (500)
    }

    //TODO: GetAllVehicle
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicle(){
        List<VehicleDTO> vehicleDTOS = vehicleService.getAllVehicle();
        if (!vehicleDTOS.isEmpty()){
            return new ResponseEntity<>(vehicleDTOS,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //TODO: Search
    @GetMapping("/search")
    public ResponseEntity<List<VehicleResponse>> searchVehicleByVehicleCategory(@RequestParam String vehicleCategory){
        List<VehicleResponse> vehicleResponses = vehicleService.getVehicleByVehicleCategory(vehicleCategory);
        if (vehicleResponses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(vehicleResponses);
    }
}
