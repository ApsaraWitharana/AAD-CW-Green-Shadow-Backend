package lk.ijse.gdse68.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffLogDetailsErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.CropDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.LogDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffLogDetailsDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffLogDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.StaffLogDetails;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.StaffLogDetailsService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffLogDetailsServiceImpl implements StaffLogDetailsService {
    @Autowired
    private final LogDAO logDAO;
    @Autowired
    private final StaffDAO staffDAO;
    @Autowired
    private final CropDAO cropDAO;
    @Autowired
    private StaffLogDetailsDAO staffLogDetailsDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaffLogDetails(LogDTO logDTO) {
        // Retrieve Crop entity based on cropCode
        Crop crop = cropDAO.findById(logDTO.getCropCode())
                .orElseThrow(() -> new RuntimeException("Crop not found for code: " + logDTO.getCropCode()));

        // Create and save Log entity, logCode is set
        Log log = new Log();
        log.setLogCode(logDTO.getLogCode());  // Set the logCode from logDTO
        log.setCrop(crop);
        log.setLogDetails(logDTO.getLogDetails());
        log.setLogDate(logDTO.getLogDate());
        log.setObservedImage(logDTO.getObservedImage());

        // Save the log to the database to ensure it has an ID for staff associations
        Log savedLog = logDAO.save(log);
        if (savedLog == null) {
            throw new DataPersistFailedException("Failed to save log details!");
        }

        // Map each StaffDTO to a Staff entity and create StaffLogDetails entries
        List<StaffLogDetails> staffLogDetailsList = logDTO.getStaffLogDetailsDTOS().stream()
                .map(staffLogDetailsDTO -> {
                    // Find the staff entity based on staff ID
                    Staff staff = staffDAO.findById(staffLogDetailsDTO.getStaff().getId())
                            .orElseThrow(() -> new RuntimeException("Staff not found for ID: " + staffLogDetailsDTO.getStaff().getId()));

                    if (!staff.getFirstName().equals(staffLogDetailsDTO.getStaff().getFirstName())) {
                        throw new RuntimeException("FirstName mismatch for staff ID: " + staff.getId());
                    }

                    // Create new StaffLogDetails and populate it
                    StaffLogDetails staffLogDetails = new StaffLogDetails();
                    staffLogDetails.setStaff(staff);
                    staffLogDetails.setFirstName(staff.getFirstName());
                    staffLogDetails.setLog(savedLog);  // Use savedLog here
                    staffLogDetails.setDescription(staffLogDetailsDTO.getDescription());
                    staffLogDetails.setWork_staff_count(staffLogDetailsDTO.getWorkStaffCount());
                    staffLogDetails.setLogDate(staffLogDetailsDTO.getLogDate());

                    return staffLogDetails;
                }).collect(Collectors.toList());

        // Save each StaffLogDetails entry in the database
        staffLogDetailsList.forEach(staffLogDetailsDAO::save);

        // Associate StaffLogDetails with Log and save the Log entity again
        savedLog.setStaffLogDetails(staffLogDetailsList);
        logDAO.save(savedLog);
    }

    @Override
    public StaffLogDetailsResponse getStaffLogDetailsById(String sl_id) {
       if (staffLogDetailsDAO.existsById(Long.valueOf(sl_id))){
           StaffLogDetails staffLogDetails = staffLogDetailsDAO.getReferenceById(Long.valueOf(sl_id));
           StaffLogDetailsDTO staffLogDetailsDTO = mapping.convertToStaffLogDetailsDTO(staffLogDetails);
           staffLogDetailsDTO.setDescription(staffLogDetails.getDescription());
           return staffLogDetailsDTO;
       }else {
           return new StaffLogDetailsErrorResponse(0,"Staff Log Details Not Found!");
       }
    }


    @Transactional
    public List<StaffLogDetailsDTO> getAllStaffLogDetails() {
        // Fetch all StaffLogDetails entities from the database
        List<StaffLogDetails> staffLogDetailsList = staffLogDetailsDAO.findAll();
        List<StaffLogDetailsDTO> staffLogDetailsDTOS = new ArrayList<>();
        for (StaffLogDetails staffLogDetails : staffLogDetailsList){
            StaffLogDetailsDTO dto = new StaffLogDetailsDTO();
            dto.setLogDate(staffLogDetails.getLogDate());
            dto.setDescription(staffLogDetails.getDescription());
            dto.setWorkStaffCount(staffLogDetails.getWork_staff_count());
            dto.setStaff(staffLogDetails.getStaff());
            staffLogDetailsDTOS.add(dto);
        }
        return staffLogDetailsDTOS;

    }

    @Override
    public String generateSLogCode() {
        Long latestId = staffLogDetailsDAO.getNextId(); // Custom query to get the next available id
        return String.format("S-LOD-%03d", latestId);    }

}