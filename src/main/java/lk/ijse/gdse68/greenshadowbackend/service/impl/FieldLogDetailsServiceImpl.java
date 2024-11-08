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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldLogDetailsServiceImpl implements FieldLogDetailsService {
    private final CropDAO cropDAO;
    private final FieldDAO fieldDAO;
    private final LogDAO logDAO;
    private final FieldLogDetailsDAO fieldLogDetailsDAO;
    private final Mapping mapping;

    @Override
    public void saveFieldLogDetails(LogDTO logDTO) {
        Crop crop = cropDAO.findById(logDTO.getCropCode())
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        // Create and save Log
        Log log = new Log();
        log.setLogCode(logDTO.getLogCode());
        log.setCrop(crop);
        log.setLogDetails(logDTO.getLogDetails());
        log.setLogDate(logDTO.getLogDate());
        log.setObservedImage(logDTO.getObservedImage());

        // Find and set associated fields
        List<Field> fields = logDTO.getFields().stream()
                .map(fieldDTO -> fieldDAO.findById(fieldDTO.getFieldCode())
                        .orElseThrow(() -> new RuntimeException("Field not found for code: " + fieldDTO.getFieldCode())))
                .collect(Collectors.toList());
        log.setFields(fields);

        Log savedLog = logDAO.save(log);
        if (savedLog == null) {
            throw new DataPersistFailedException("Failed to save log details!");
        }

        // Create and save FieldLogDetails
        for (FieldLogDetailsDTO fieldLogDetailsDTO : logDTO.getFieldLogDetailsDTOS()) {
            Field field = fieldDAO.findById(fieldLogDetailsDTO.getField_code())
                    .orElseThrow(() -> new RuntimeException("Field code not found: " + fieldLogDetailsDTO.getField_code()));

            FieldLogDetails fieldLogDetails = new FieldLogDetails();
            fieldLogDetails.setField_code(fieldLogDetailsDTO.getField_code());
            fieldLogDetails.setLog_code(fieldLogDetailsDTO.getLog_code());
            fieldLogDetails.setWork_field_count(fieldLogDetailsDTO.getWork_fields_count());
            fieldLogDetails.setDescription(fieldLogDetailsDTO.getDescription());
            fieldLogDetails.setDate(fieldLogDetailsDTO.getDate());

            fieldLogDetailsDAO.save(fieldLogDetails);
        }
    }
}
