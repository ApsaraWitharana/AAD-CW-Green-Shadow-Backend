package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.CropNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.CropService;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("api/v1/crop") //rest full ekk -end point ek
@RequiredArgsConstructor
public class CropController {
    @Autowired
    private final CropService cropService;

    //TODO: CRUD Implement
    //TODO:SAVE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCrop(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("cropCommonName") String cropCommonName,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("category") String category,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("fieldCode") String fieldCode ){

        try {
            String[] base64Images = AppUtil.toBase64Images(cropImage);
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCropCommonName(cropCommonName);
            cropDTO.setCropScientificName(cropScientificName);
            cropDTO.setCropImage(cropImage);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Update
    @PatchMapping(value = "/{cropCode}")
    public ResponseEntity<String> updateField(
            @RequestPart("updateCropCommonName") String updateCropCommonName,
            @RequestPart("updateCropScientificName") String updateCropScientificName,
            @RequestPart("updateCropImage") MultipartFile updateCropImage,
            @RequestPart("updateCategory") String updateCategory,
            @RequestPart("updateCropSeason") String updateCropSeason,
            @RequestPart("updateFieldCode") String updateFieldCode,
            @RequestPart("cropCode") String cropCode){
        try {
            String[] base64Images = AppUtil.toBase64Images(updateCropImage);
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCropCommonName(updateCropCommonName);
            cropDTO.setCropScientificName(updateCropScientificName);
            cropDTO.setCropImage(updateCropImage);
            cropDTO.setCategory(updateCategory);
            cropDTO.setCropSeason(updateCropSeason);
            cropDTO.setFieldCode(updateFieldCode);
            cropService.updateCrop(cropCode,cropDTO);
            return new ResponseEntity<>("Crop Details Update Successfully!!",HttpStatus.OK);

        } catch ( ClassNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Delete
    @DeleteMapping("/{cropCode}")
    public ResponseEntity<String> deleteCrop(@PathVariable ("cropCode") String cropCode){
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>("Crop Details Delete Successfully!!",HttpStatus.OK);
        }catch (CropNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>("Crop not found!!",HttpStatus.NOT_FOUND);
        }
    }
}
