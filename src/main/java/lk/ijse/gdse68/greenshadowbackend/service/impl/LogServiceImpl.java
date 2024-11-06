package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.LogErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.LogResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.CropDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.LogDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.FieldNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.exception.LogNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.LogService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author : sachini
 * @date : 2024-11-06
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private LogDAO logDAO;
    @Autowired
    private CropDAO cropDAO;
    @Override
    public void saveLog(LogDTO logDTO) {
        logDTO.setLogCode(logDTO.getLogCode());
        Log saveLogs = logDAO.save(mapping.convertToLogEntity(logDTO));
        if (saveLogs == null){
            throw new DataPersistFailedException("Logs save not found!!");
        }
    }

    @Override
    public void updateLog(LogDTO logDTO) {
        Optional<Log> tmpLogEntity = logDAO.findById(logDTO.getLogCode());
        if (!tmpLogEntity.isPresent()){
            throw new LogNotFoundException("Log update not found!!");
        }else {
            Log log = tmpLogEntity.get();
            log.setLogCode(logDTO.getLogCode());
            log.setLogDate(logDTO.getLogDate());
            log.setLogDetails(logDTO.getLogDetails());
            log.setObservedImage(logDTO.getObservedImage());
            // Retrieve and set Crop base cropCode
            if (logDTO.getCropCode() != null) {
                Crop crop = cropDAO.findById(logDTO.getCropCode())
                        .orElseThrow(() -> new FieldNoteFoundException("Log with code " + logDTO.getCropCode() + " not found"));
                log.setCrop(crop);
            }
            logDAO.save(log);
        }
    }

    @Override
    public void deleteLog(String logCode) {

        Optional<Log> selectLogCode = logDAO.findById(logCode);
        if (selectLogCode.isPresent()){
            logDAO.deleteById(logCode);
        }else {
            throw new LogNotFoundException("Log details not found!!");
        }
    }

    @Override
    public LogResponse getSelectedLog(String logCode) {
        if (logDAO.existsById(logCode)) {
            Log log = logDAO.getReferenceById(logCode);
            LogDTO logDTO = mapping.convertToLogDTO(log);
            logDTO.setLogDate(logDTO.getLogDate());
            return logDTO;
        }else {
            return new LogErrorResponse(0,"Log details not found!!");
        }
    }

    @Override
    public List<LogDTO> getAllLog() {
        return null;
    }
}
