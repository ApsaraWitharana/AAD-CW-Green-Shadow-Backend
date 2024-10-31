package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.StaffNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : sachini
 * @date : 2024-10-30
 **/
@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor

public class StaffController {

    @Autowired
    private final StaffService staffService;

    //TODO:Staff crud implement
    //TODO: Save
    @PostMapping
    public ResponseEntity<String> saveStaff(@RequestBody StaffDTO staffDTO) {
        try {
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Staff data could not be saved, data persistence failed.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error occurred while saving the staff.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO: Update staff
    @PatchMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStaff(@PathVariable("id") String id , @RequestBody StaffDTO staffDTO){
        try {
            staffService.updateStaff(id,staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNoteFoundException e){
            return new ResponseEntity<>("Staff not found!",HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO:Delete staff member
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStaffMember(@PathVariable("id") String id){
        try {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Deletion successful
        }catch (StaffNoteFoundException e){
            return new ResponseEntity<>("Staff not found!",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
