package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.EquipmentDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-02
 **/
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v1/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;
    Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    //TODO:Equipment CRUD
    //TODO:Save
    @PostMapping
    public ResponseEntity<String> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            logger.info("Equipment Details Save Successfully!!");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Equipment data could not be saved, data persistence failed.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error occurred while saving the Equipment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO: Update
    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> updateEquipment(@PathVariable ("id") String id,@RequestBody EquipmentDTO equipmentDTO){
        try {
            equipmentService.updateEquipment(id,equipmentDTO);
            logger.info("Equipment details Update Successfully!!");
            return new ResponseEntity<>("Equipment Update Successfully!!",HttpStatus.OK);

        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>("Equipment not found!!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO: Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEquipments(@PathVariable ("id") String id){
        try {
            equipmentService.deleteEquipment(id);
            logger.info("Equipments details Delete Successfully!!");
            return new ResponseEntity<>("Equipments Delete Successfully!!",HttpStatus.OK);
        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>("Equipments not found!!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO:Get
    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipmentResponse> getSelectEquipment(@PathVariable ("id") String id){
        EquipmentResponse equipmentResponse = equipmentService.getSelectedEquipment(id);
        if (equipmentResponse instanceof EquipmentDTO){
            return new ResponseEntity<>(equipmentResponse,HttpStatus.OK);
        }else if (equipmentResponse instanceof EquipmentErrorResponse){
            return new ResponseEntity<>(equipmentResponse,HttpStatus.NOT_FOUND);
        }
        logger.error("Error fetching crop: ");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //TODO:Get All
    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment(){
        List<EquipmentDTO> equipmentDTOS = equipmentService.getAllEquipment();
        if (!equipmentDTOS.isEmpty()){
            return new ResponseEntity<>(equipmentDTOS,HttpStatus.OK);
        }else {
            logger.error("Get All crop!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //TODO: Search
    @GetMapping("/search")
    public ResponseEntity<List<EquipmentResponse>> searchEquipmentByEquipmentName(@RequestParam String name){
        List<EquipmentResponse> equipmentResponses = equipmentService.getEquipmentByName(name);
        if (equipmentResponses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(equipmentResponses);
    }
}
