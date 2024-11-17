package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffFieldDetailsDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffFieldDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.StaffFieldDetails;
import lk.ijse.gdse68.greenshadowbackend.service.StaffFieldDetailsService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffFieldDetailsServiceImpl implements StaffFieldDetailsService {
    @Autowired
    private final StaffDAO staffDAO;
    @Autowired
    private final FieldDAO fieldDAO;
    @Autowired
    private final StaffFieldDetailsDAO staffFieldDetailsDAO;
    @Autowired
    private ModelMapper mapper;

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

            if (!staff.getFirstName().equals(staffFieldDetailsDTO.getStaff().getFirstName())) {
                throw new RuntimeException("FirstName mismatch for staff ID: " + staff.getId());
            }
            Field field = fieldDAO.findById(staffFieldDetailsDTO.getField().getFieldCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Field Code: " + staffFieldDetailsDTO.getField().getFieldCode()));

            // Check if the staff is already assigned to the field
            long count = staffFieldDetailsDAO.countByStaffAndField((staff.getId()), field.getFieldCode());
            if (count > 0) {
                throw new IllegalArgumentException("Staff is already assigned to the field.");
            }

            // Create and populate StaffFieldDetails entity
            StaffFieldDetails staffFieldDetails = new StaffFieldDetails();
            staffFieldDetails.setStaff(staff);
            staffFieldDetails.setFirstName(staff.getFirstName());
            staffFieldDetails.setField(field);
            staffFieldDetails.setStatus(staffFieldDetailsDTO.getStatus());
            staffFieldDetails.setDescription(staffFieldDetailsDTO.getDescription());
            staffFieldDetails.setWork_staff_count(staffFieldDetailsDTO.getWorkStaffCount());
            staffFieldDetails.setDate(staffFieldDetailsDTO.getDate());

            // Save the entity using custom query method
            staffFieldDetailsDAO.save(staffFieldDetails);
        } catch (Exception e) {
            System.err.println("Error saving staff field details: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("An error occurred while saving staff field details.", e);
        }
    }
    @Transactional
    public List<StaffFieldDetailsDTO> getAllStaffFieldDetails() {
        List<StaffFieldDetails> staffFieldDetailsList = staffFieldDetailsDAO.findAll();
        List<StaffFieldDetailsDTO> staffFieldDetailsDTOS = new ArrayList<>();
        for (StaffFieldDetails staffFieldDetails : staffFieldDetailsList) {
            StaffFieldDetailsDTO dto = new StaffFieldDetailsDTO();
            dto.setStatus(staffFieldDetails.getStatus());
            dto.setDescription(staffFieldDetails.getDescription());
            dto.setWorkStaffCount(staffFieldDetails.getWork_staff_count());
            dto.setDate(staffFieldDetails.getDate());
            dto.setStaff(staffFieldDetails.getStaff());
            dto.setField(staffFieldDetails.getField());
            staffFieldDetailsDTOS.add(dto);
        }

        return staffFieldDetailsDTOS;
    }

    @Override
    public String generateSFieldCode() {
        // Get the latest id in the logs table
        Long latestId = staffFieldDetailsDAO.getNextId(); // Custom query to get the next available id
        return String.format("S-FLD-%03d", latestId);
    }

}