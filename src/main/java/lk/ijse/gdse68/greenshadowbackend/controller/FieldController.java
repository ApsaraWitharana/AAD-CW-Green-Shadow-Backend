package lk.ijse.gdse68.greenshadowbackend.controller;

import jakarta.persistence.Access;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.FieldNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.FieldService;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:63342") // Or use "*" to allow all origins
@RestController
@RequestMapping("api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private final FieldService fieldService;

    //Handle Preflight (OPTIONS) Requests: CORS preflight requests are automatically handled by Spring Boot if CORS is properly configured.
//    @RequestMapping(method = RequestMethod.OPTIONS)
//    public ResponseEntity<Void> handleOptions() {
//        return ResponseEntity.ok().build();
//    }
    //TODO: Field CRUD Implement
    //TODO: Save Filed
    @PostMapping
    public ResponseEntity<String> saveField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2){
        try {
            // Get Base64 strings for both images
            String[] base64Images = AppUtil.toBase64Images(fieldImage1, fieldImage2);

            // Create and populate FieldDTO
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setExtentSize(Double.parseDouble(extentSize));
            fieldDTO.setFieldImages(fieldImage1, fieldImage2); // Pass MultipartFile images for field
            // Save the field data
            fieldService.saveField(fieldDTO);
            return ResponseEntity.ok("Field saved successfully!");
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Field data could not be saved, data persistence failed.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error occurred while saving the field.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

   //TODO:Update
    @PatchMapping(value = "/{fieldCode}")
    public ResponseEntity<String> updateField(
            @RequestPart("updateFieldName") String updateFieldName,
            @RequestPart("updateFieldLocation") String updateFieldLocation,
            @RequestPart("updateExtentSize") String updateExtentSize,
            @RequestPart("updateFieldImage1") MultipartFile updateFieldImage1,
            @RequestPart("updateFieldImage2") MultipartFile updateFieldImage2,
            @RequestPart("fieldCode") String fieldCode) {
        try {
            String[] base64Images = AppUtil.toBase64Images(updateFieldImage1,updateFieldImage2);

            FieldDTO buildfieldDTO = new FieldDTO();
            buildfieldDTO.setFieldCode(fieldCode);
            buildfieldDTO.setFieldName(updateFieldName);
            buildfieldDTO.setFieldLocation(updateFieldLocation);
            buildfieldDTO.setExtentSize(Double.valueOf(updateExtentSize));
            buildfieldDTO.setFieldImages(updateFieldImage1, updateFieldImage2); // Pass MultipartFile images for field
            fieldService.updateField(buildfieldDTO);
            return new ResponseEntity<>("Field Update Successfully!!",HttpStatus.OK);
        } catch (FieldNoteFoundException | ClassNotFoundException e) {
            return new ResponseEntity<>("Field not found!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Delete
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<String> deleteField(@PathVariable ("fieldCode") String fieldCode){
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>("Field Details Delete Successfully!!",HttpStatus.OK);
        }catch (FieldNoteFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>("Field not found!",HttpStatus.NOT_FOUND);
        }
    }

    //TODO: Select id
    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldResponse> getSelectedField(@PathVariable ("fieldCode") String fieldCode){
        FieldResponse fieldResponse = fieldService.getSelectedField(fieldCode);
        if (fieldResponse instanceof FieldDTO){
            return new ResponseEntity<>(fieldResponse,HttpStatus.OK);
        }else if (fieldResponse instanceof FieldErrorResponse){
            return new ResponseEntity<>(fieldResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    //TODO:GetAll
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllField(){
        List<FieldDTO> fieldDTOS = fieldService.getAllField();
        if (!fieldDTOS.isEmpty()){
            return new ResponseEntity<>(fieldDTOS,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //TODO:Search
    @GetMapping("/search")
    public ResponseEntity<List<FieldResponse>> searchFieldByFieldName(@RequestParam String fieldName){
        List<FieldResponse> fieldResponses = fieldService.getFieldByFieldName(fieldName);
        if (fieldResponses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        }
        return ResponseEntity.ok(fieldResponses);
    }

}

