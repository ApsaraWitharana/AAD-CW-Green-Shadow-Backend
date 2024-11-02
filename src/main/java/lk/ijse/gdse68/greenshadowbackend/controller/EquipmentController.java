package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.EquipmentDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : sachini
 * @date : 2024-11-02
 **/
@RestController
@RequestMapping("api/v1/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;

    //TODO:Equipment CRUD
    //TODO:Save
    @PostMapping
    public ResponseEntity<String> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Equipment data could not be saved, data persistence failed.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error occurred while saving the Equipment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEquipment(@PathVariable ("id") String id,@RequestBody EquipmentDTO equipmentDTO){
        try {
            equipmentService.updateEquipment(id,equipmentDTO);
            return new ResponseEntity<>("Equipment Update Successfully!!",HttpStatus.OK);

        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>("Equipment not found!!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEquipments(@PathVariable ("id") String id){
        try {
            equipmentService.deleteEquipment(id);
            return new ResponseEntity<>("Equipments Delete Successfully!!",HttpStatus.OK);
        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>("Equipments not found!!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
