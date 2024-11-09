package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffLogDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.service.StaffLogDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/staff-log-details")
public class StaffLogDetailsController {

    @Autowired
    private final StaffLogDetailsService staffLogDetailsService;

    public StaffLogDetailsController(StaffLogDetailsService staffLogDetailsService) {
        this.staffLogDetailsService = staffLogDetailsService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStaffLogDetails(@RequestBody LogDTO logDTO) {
        try {
            staffLogDetailsService.saveStaffLogDetails(logDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Staff log details saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving staff log details: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffLogDetailsResponse> getAllStaffLogDetails(@PathVariable ("id") String id) {
        StaffLogDetailsResponse staffLogDetailsResponse = staffLogDetailsService.getStaffLogDetailsById(id);
        if (staffLogDetailsResponse instanceof StaffLogDetailsDTO){
            return new ResponseEntity<>(staffLogDetailsResponse,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(staffLogDetailsResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StaffLogDetailsDTO>> getStaffLogDetailsById() {
        List<StaffLogDetailsDTO> staffLogDetailsDTOS = staffLogDetailsService.getAllStaffLogDetails();
        return ResponseEntity.ok(staffLogDetailsDTOS);
    }
}
