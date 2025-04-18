package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.LogResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-06
 **/
public interface LogService {
    String generateNextLogCode();
    void saveLog(LogDTO logDTO);
    void updateLog(LogDTO logDTO);
    void deleteLog(String logCode);
    LogResponse getSelectedLog(String logCode);
    List<LogDTO> getAllLog();

    List<LogResponse> getLogByLogCode(String logCode);
}
