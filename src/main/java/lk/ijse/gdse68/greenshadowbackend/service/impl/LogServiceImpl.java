package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.LogResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.LogDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.LogService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    }

    @Override
    public void deleteLog(String logCode) {

    }

    @Override
    public LogResponse getSelectedLog(String logCode) {
        return null;
    }

    @Override
    public List<LogDTO> getAllLog() {
        return null;
    }
}
