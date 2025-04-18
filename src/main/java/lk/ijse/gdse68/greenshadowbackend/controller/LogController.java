package lk.ijse.gdse68.greenshadowbackend.controller;


import lk.ijse.gdse68.greenshadowbackend.customerObj.CropResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.LogErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.LogResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.LogNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.LogService;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor

public class LogController {
    @Autowired
    private final LogService logService;
    Logger logger = LoggerFactory.getLogger(LogController.class);


    //TODO: Generate Log code
    @GetMapping("/nextLog")
    public ResponseEntity<String> getNextLogCode(){
        String nextLogCode = logService.generateNextLogCode();
        return ResponseEntity.ok(nextLogCode);
    }

    //TODO: Log CRUD Implement
    //TODO:Save method
    @PostMapping
    public ResponseEntity<String> saveLogs(
            @RequestParam("logCode") String logCode,
            @RequestParam("logDate") Date logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("observedImage") MultipartFile observedImage,
            @RequestParam("cropCode") String cropCode) {
        try {
            String[] base64Images = AppUtil.toBase64Images(observedImage);
            LogDTO logDTO = new LogDTO();
            logDTO.setLogCode(logCode);
            logDTO.setLogDate(logDate);
            logDTO.setLogDetails(logDetails);
            logDTO.setLogImage(observedImage);
            logDTO.setCropCode(cropCode);
            logService.saveLog(logDTO);
            logger.info("Log Details Save Successfully!!");
            return new ResponseEntity<>("Log details saved successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //TODO:Update
    @PatchMapping(value = "/{logCode}")
    public ResponseEntity<String> updateLogs(
            @RequestParam("updateLogDate") Date updateLogDate,
            @RequestParam("updateLogDetails") String updateLogDetails,
            @RequestParam("updateObservedImage") MultipartFile updateObservedImage,
            @RequestParam("updateCropCode") String updateCropCode,
            @RequestParam("logCode") String logCode){
        try {
            String[] base64Images = AppUtil.toBase64Images(updateObservedImage);
            LogDTO logDTO = new LogDTO();
            logDTO.setLogCode(logCode);
            logDTO.setLogDate(updateLogDate);
            logDTO.setLogDetails(updateLogDetails);
            logDTO.setLogImage(updateObservedImage);
            logDTO.setCropCode(updateCropCode);
            logService.updateLog(logDTO);
            logger.info("Log details Update Successfully!!");
            return new ResponseEntity<>("Log Update Successfully!!",HttpStatus.OK);
        }catch (LogNotFoundException e){
            return new ResponseEntity<>("Log Details update not found!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }

        //TODO:Delete
    @DeleteMapping(value = "/{logCode}")
     public ResponseEntity<String> deleteLog(@PathVariable ("logCode") String logCode){
        try {
            logService.deleteLog(logCode);
            logger.info("Log details Delete Successfully!!");
            return new ResponseEntity<>("Log Details Delete Successfully!!",HttpStatus.OK);
        }catch (LogNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>("Log is not found!!",HttpStatus.NOT_FOUND);
        }
     }

     //TODO:Get select id
    @GetMapping(value = "/{logCode}")
    public ResponseEntity<LogResponse> getAllLog(@PathVariable ("logCode") String logCode){
        LogResponse logResponse = logService.getSelectedLog(logCode);
        if (logResponse instanceof LogDTO){
            return new ResponseEntity<>(logResponse,HttpStatus.OK);
        }else if (logResponse instanceof LogErrorResponse){
            return new ResponseEntity<>(logResponse,HttpStatus.NOT_FOUND);
        }
        logger.error("Error fetching log: ");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //TODO:Get all
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LogDTO>> getAllLogs(){
        List<LogDTO> logDTOS = logService.getAllLog();
        if (!logDTOS.isEmpty()){
            return new ResponseEntity<>(logDTOS,HttpStatus.OK);
        }else {
            logger.error("Get All log!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<LogResponse>> searchLogByLogCode(@RequestParam String logCode){
        List<LogResponse> logResponses = logService.getLogByLogCode(logCode);
        if (logResponses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(logResponses);
    }

}

