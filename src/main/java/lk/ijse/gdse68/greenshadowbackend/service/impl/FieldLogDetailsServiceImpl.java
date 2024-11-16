package lk.ijse.gdse68.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.greenshadowbackend.dao.CropDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.FieldLogDetailsDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.LogDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldLogDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.FieldLogDetails;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.FieldLogDetailsService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldLogDetailsServiceImpl implements FieldLogDetailsService {
    @Autowired
    private final CropDAO cropDAO;
    @Autowired
    private final FieldDAO fieldDAO;
    @Autowired
    private final LogDAO logDAO;
    @Autowired
    private final FieldLogDetailsDAO fieldLogDetailsDAO;

@Override
public void saveFieldLogDetails(LogDTO logDTO) {
    // Retrieve Crop entity based on cropCode
    Crop crop = cropDAO.findById(logDTO.getCropCode())
            .orElseThrow(() -> new RuntimeException("Crop not found for code: " + logDTO.getCropCode()));

    // Create and save Log
    Log log = new Log();
    log.setLogCode(logDTO.getLogCode());
    log.setCrop(crop);
    log.setLogDetails(logDTO.getLogDetails());
    log.setLogDate(logDTO.getLogDate());
    log.setObservedImage(logDTO.getObservedImage());

    Log savedLog = logDAO.save(log);
    if (savedLog == null) {
        throw new DataPersistFailedException("Failed to save log details!");
    }

    // Map each FieldLogDetailsDTO to FieldLogDetails entity
    List<FieldLogDetails> fieldLogDetailsList = logDTO.getFieldLogDetailsDTOS().stream()
            .map(fieldLogDetailsDTO -> {
                Field field = fieldDAO.findById(fieldLogDetailsDTO.getField().getFieldCode())
                        .orElseThrow(() -> new RuntimeException("Field not found for ID: " + fieldLogDetailsDTO.getField().getFieldCode()));

                // Create new FieldLogDetails and set its properties from DTO
                FieldLogDetails fieldLogDetails = new FieldLogDetails();
                fieldLogDetails.setField(field);
                fieldLogDetails.setLog(savedLog); // Use savedLog here
                fieldLogDetails.setDescription(fieldLogDetailsDTO.getDescription());
                fieldLogDetails.setWork_field_count(fieldLogDetailsDTO.getWorkFieldsCount());
                fieldLogDetails.setLogDate(logDTO.getLogDate());

                return fieldLogDetails;
            }).collect(Collectors.toList());

    // Save each FieldLogDetails to the database
    fieldLogDetailsList.forEach(fieldLogDetailsDAO::save);

    // Associate FieldLogDetails with Log and save the Log entity again
    savedLog.setFieldLogDetails(fieldLogDetailsList);
    logDAO.save(savedLog);
}

    @Transactional
    public List<FieldLogDetailsDTO> getAllFieldLogDetails() {
        List<FieldLogDetails> fieldLogDetailsList = fieldLogDetailsDAO.findAll();
        List<FieldLogDetailsDTO> fieldLogDetailsDTOS = new ArrayList<>();

        for (FieldLogDetails fieldLogDetails : fieldLogDetailsList) {
            FieldLogDetailsDTO dto = new FieldLogDetailsDTO();
            // Map other attributes
            dto.setDescription(fieldLogDetails.getDescription());
            dto.setWorkFieldsCount(fieldLogDetails.getWork_field_count());
            dto.setLogDate(fieldLogDetails.getLogDate());

            fieldLogDetailsDTOS.add(dto);
        }

        return fieldLogDetailsDTOS;
    }

    public String generateLogCode() {
        // Get the latest id in the logs table
        Long latestId = fieldLogDetailsDAO.getNextId(); // Custom query to get the next available id
        return String.format("F-LOG-%03d", latestId);
    }

}

