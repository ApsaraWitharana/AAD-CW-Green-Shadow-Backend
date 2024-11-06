package lk.ijse.gdse68.greenshadowbackend.controller;


import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.service.LogService;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
@CrossOrigin
@RestController
@RequestMapping("api/v1/log")
@RequiredArgsConstructor

public class LogController {
    @Autowired
    private final LogService logService;

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
            return new ResponseEntity<>("Log details saved successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

