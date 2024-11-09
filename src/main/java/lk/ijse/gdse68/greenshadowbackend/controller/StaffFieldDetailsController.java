package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.StaffFieldDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.service.StaffFieldDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff-field-details")
@RequiredArgsConstructor
public class StaffFieldDetailsController {

    private final StaffFieldDetailsService staffFieldDetailsService;

    @PostMapping("/save")
    public ResponseEntity<String> saveStaffFieldDetails(@RequestBody StaffFieldDetailsDTO staffFieldDetailsDTO) {
        try {
            staffFieldDetailsService.saveStaffFieldDetails(staffFieldDetailsDTO);
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
        return ResponseEntity.ok(staffFieldDetailsDTOS);
    }
}
