package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.StaffNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-30
 **/
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class StaffController {

    @Autowired
    private final StaffService staffService;

    Logger logger = LoggerFactory.getLogger(StaffController.class);

    //TODO: Generate Staff Code
    @GetMapping("/nextStaff")
    public ResponseEntity<String> getNextStaffId(){
        String nextStaffId = staffService.generateNextStaffId();
        return ResponseEntity.ok(nextStaffId);
    }

    //TODO:Staff crud implement
    //TODO: Save
    @PostMapping
    public ResponseEntity<String> saveStaff(@RequestBody StaffDTO staffDTO) {
        try {
            staffService.saveStaff(staffDTO);
            logger.info("Staff Details Save Successfully!!");
            return new ResponseEntity<>("Staff member created successfully", HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Staff data could not be saved: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Update staff
    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable("id") String id, @RequestBody StaffDTO staffDTO) {
        try {
            staffService.updateStaff(id, staffDTO);
            logger.info("Staff details Update Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNoteFoundException e) {
            return new ResponseEntity<>("Staff not found!", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Delete staff member
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStaffMember(@PathVariable("id") String id) {
        try {
            staffService.deleteStaff(id);
            logger.info("Staff details Delete Successfully!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Deletion successful
        } catch (StaffNoteFoundException e) {
            return new ResponseEntity<>("Staff not found!", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: GetSelectStaffMemberId
    @GetMapping(value = "/{id}")
    public ResponseEntity<StaffResponse> getSelectStaffMember(@PathVariable("id") String id) {
        StaffResponse staffResponse = staffService.getSelectedStaff(id);
        if (staffResponse instanceof StaffDTO) {
            return new ResponseEntity<>(staffResponse, HttpStatus.OK);
        } else if (staffResponse instanceof StaffErrorResponse) {
            return new ResponseEntity<>(staffResponse, HttpStatus.NOT_FOUND);
        }
        logger.error("Error fetching staff: ");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //TODO:GetAllStaff
    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<StaffDTO> staffDTOS = staffService.getAllStaff();
        if (!staffDTOS.isEmpty()) {
            return new ResponseEntity<>(staffDTOS, HttpStatus.OK);
        } else {
            logger.error("Get All staff!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //TODO:Search name
    @GetMapping("/search")
    public ResponseEntity<List<StaffResponse>> searchStaffByFirstName(@RequestParam String firstName) {
        List<StaffResponse> staffResponses = staffService.getStaffByFirstName(firstName);
        if (staffResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(staffResponses);
    }
}