package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.CropResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;

import java.util.List;

public interface CropService {
    String generateNextCropCode();

    void saveCrop(CropDTO cropDTO);
    void updateCrop(String cropCode,CropDTO cropDTO) throws ClassNotFoundException;
    void deleteCrop(String cropCode);
    CropResponse getSelectedCrop(String cropCode);
    List<CropDTO> getAllCrop();
    List<CropResponse> getCropByCropCommonName(String CropCommonName);
}
