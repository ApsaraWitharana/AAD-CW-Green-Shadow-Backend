package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffFieldDetailsDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffFieldDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.StaffFieldDetails;
import lk.ijse.gdse68.greenshadowbackend.service.StaffFieldDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffFieldDetailsServiceImpl implements StaffFieldDetailsService {
    private final StaffDAO staffDAO;
    private final FieldDAO fieldDAO;
    private final StaffFieldDetailsDAO staffFieldDetailsDAO;

    @Override
    public void saveStaffFieldDetails(StaffFieldDetailsDTO staffFieldDetailsDTO) {
        try {
            // Validate that Staff and Field IDs are present
            if (staffFieldDetailsDTO.getStaff() == null || staffFieldDetailsDTO.getStaff().getId() == null) {
                throw new IllegalArgumentException("Staff ID is missing in the provided DTO.");
            }
            if (staffFieldDetailsDTO.getField() == null || staffFieldDetailsDTO.getField().getFieldCode() == null) {
                throw new IllegalArgumentException("Field code is missing in the provided DTO.");
            }

            // Retrieve Staff and Field entities from the database using the provided IDs
            Staff staff = staffDAO.findById(staffFieldDetailsDTO.getStaff().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Staff ID: " + staffFieldDetailsDTO.getStaff().getId()));

            Field field = fieldDAO.findById(staffFieldDetailsDTO.getField().getFieldCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Field Code: " + staffFieldDetailsDTO.getField().getFieldCode()));

            // Create and populate StaffFieldDetails entity
            StaffFieldDetails staffFieldDetails = new StaffFieldDetails();
            staffFieldDetails.setStaff(staff);
            staffFieldDetails.setField(field);
            staffFieldDetails.setDescription(staffFieldDetailsDTO.getDescription());
            staffFieldDetails.setWork_staff_count(staffFieldDetailsDTO.getWorkStaffCount());
            staffFieldDetails.setDate(staffFieldDetailsDTO.getDate());

            // Save the entity
            staffFieldDetailsDAO.save(staffFieldDetails);
        } catch (Exception e) {
            System.err.println("Error saving staff field details: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("An error occurred while saving staff field details.", e);
        }
    }

    @Override
    public List<StaffFieldDetailsDTO> getAllStaffFieldDetails() {
        // Retrieve all StaffFieldDetails entities
        List<StaffFieldDetails> staffFieldDetailsList = staffFieldDetailsDAO.findAll();

        // Convert them to StaffFieldDetailsDTO objects
        List<StaffFieldDetailsDTO> staffFieldDetailsDTOS = new ArrayList<>();
        for (StaffFieldDetails staffFieldDetails : staffFieldDetailsList) {
            StaffFieldDetailsDTO staffFieldDetailsDTO = new StaffFieldDetailsDTO();
            staffFieldDetailsDTO.setStaff(staffFieldDetails.getStaff());
            staffFieldDetailsDTO.setField(staffFieldDetails.getField());
            staffFieldDetailsDTO.setDescription(staffFieldDetails.getDescription());
            staffFieldDetailsDTO.setWorkStaffCount(staffFieldDetails.getWork_staff_count());
            staffFieldDetailsDTO.setDate(staffFieldDetails.getDate());

            staffFieldDetailsDTOS.add(staffFieldDetailsDTO);
        }

        return staffFieldDetailsDTOS;
    }
}
