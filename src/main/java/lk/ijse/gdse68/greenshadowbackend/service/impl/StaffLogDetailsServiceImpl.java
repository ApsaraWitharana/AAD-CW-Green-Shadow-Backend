package lk.ijse.gdse68.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.greenshadowbackend.dao.CropDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.LogDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffLogDetailsDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffLogDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.StaffLogDetails;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.StaffLogDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

                    // Create new StaffLogDetails and populate it
                    StaffLogDetails staffLogDetails = new StaffLogDetails();
                    staffLogDetails.setStaff(staff);
                    staffLogDetails.setLog(savedLog);  // Use savedLog here
                    staffLogDetails.setDescription(staffLogDetailsDTO.getDescription());
                    staffLogDetails.setWork_staff_count(staffLogDetailsDTO.getWorkStaffCount());
                    staffLogDetails.setDate(staffLogDetailsDTO.getDate());

                    return staffLogDetails;
                }).collect(Collectors.toList());

        // Save each StaffLogDetails entry in the database
        staffLogDetailsList.forEach(staffLogDetailsDAO::save);

        // Associate StaffLogDetails with Log and save the Log entity again
        savedLog.setStaffLogDetails(staffLogDetailsList);
        logDAO.save(savedLog);
    }
}