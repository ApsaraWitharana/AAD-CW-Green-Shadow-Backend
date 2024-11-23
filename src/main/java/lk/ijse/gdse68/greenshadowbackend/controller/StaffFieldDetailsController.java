package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.ResponseDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffFieldDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.service.StaffFieldDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v1/staff-field-details")
@RequiredArgsConstructor
public class StaffFieldDetailsController {
     @Autowired
    private final StaffFieldDetailsService staffFieldDetailsService;
    Logger logger = LoggerFactory.getLogger(StaffFieldDetailsDTO.class);

    @PostMapping("/save")
    public ResponseEntity<String> saveStaffFieldDetails(@RequestBody StaffFieldDetailsDTO staffFieldDetailsDTO) {
        try {
            staffFieldDetailsService.saveStaffFieldDetails(staffFieldDetailsDTO);
            logger.info("StaffField Details Save Successfully!!");
            return ResponseEntity.status(HttpStatus.CREATED).body("Staff field details saved successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving staff field details.");
        }
    }
    @GetMapping
    public ResponseEntity<List<StaffFieldDetailsDTO>> getAllStaffFieldDetails() {
        List<StaffFieldDetailsDTO> staffFieldDetailsDTOS = staffFieldDetailsService.getAllStaffFieldDetails();
        if (!staffFieldDetailsDTOS.isEmpty()){
            return new ResponseEntity<>(staffFieldDetailsDTOS,HttpStatus.OK);
        }
        logger.error("Get All StaffField!!");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generate-staffField-code")
    public ResponseEntity<String> generateLogCode() {
        String logCode = staffFieldDetailsService.generateSFieldCode();
        return ResponseEntity.ok(logCode);
    }
}
