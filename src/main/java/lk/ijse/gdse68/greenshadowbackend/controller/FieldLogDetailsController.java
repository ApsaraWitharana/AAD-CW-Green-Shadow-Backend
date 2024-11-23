package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.FieldLogDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.service.FieldLogDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v1/field-log-details")
public class FieldLogDetailsController {

    private final FieldLogDetailsService fieldLogDetailsService;
    Logger logger = LoggerFactory.getLogger(FieldLogDetailsController.class);

    @Autowired
    public FieldLogDetailsController(FieldLogDetailsService fieldLogDetailsService) {
        this.fieldLogDetailsService = fieldLogDetailsService;
    }

    // Endpoint to create field log details
    @PostMapping("/save")
    public ResponseEntity<String> createFieldLogDetails(@RequestBody LogDTO logDTO) {
        try {
            fieldLogDetailsService.saveFieldLogDetails(logDTO);
            logger.info("fieldLogDetails Save Successfully!!");
            return ResponseEntity.ok("Field log details saved successfully!!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save field log details: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public  ResponseEntity<List<FieldLogDetailsDTO>> getAllFieldLogDetails() {
        List<FieldLogDetailsDTO> fieldLogDetailsDTOS = fieldLogDetailsService.getAllFieldLogDetails();
        if (!fieldLogDetailsDTOS.isEmpty()){
            return new ResponseEntity<>(fieldLogDetailsDTOS, HttpStatus.OK);
        }else {
            logger.error("Get All fieldLogDetails!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // Call the service layer to get the field log details
    }

    @GetMapping("/generate-log-code")
    public ResponseEntity<String> generateLogCode() {
        String logCode = fieldLogDetailsService.generateLogCode();
        return ResponseEntity.ok(logCode);
    }
}
