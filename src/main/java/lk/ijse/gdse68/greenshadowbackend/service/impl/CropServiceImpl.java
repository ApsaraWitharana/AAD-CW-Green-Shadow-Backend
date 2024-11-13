package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.CropErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.CropResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.CropDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.exception.CropNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.CropService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : sachini
 * @date : 2024-11-01
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(cropDTO.getCropCode());
        Crop saveCrop = cropDAO.save(mapping.convertToCropEntity(cropDTO));
        if (saveCrop == null){
            throw new DataPersistFailedException("Crop cannot data saved!!");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) throws ClassNotFoundException {
        Optional<Crop> tmpCropEntity = cropDAO.findById(cropCode);
        if (!tmpCropEntity.isPresent()){
            throw new ClassNotFoundException("Crop update not found!");
        }else {
            Crop crop = tmpCropEntity.get();
            crop.setCropCommonName(cropDTO.getCropCommonName());
            crop.setCropScientificName(cropDTO.getCropScientificName());
            crop.setCropImage(cropDTO.getCropImage());
            crop.setCategory(cropDTO.getCategory());
            crop.setCropSeason(cropDTO.getCropSeason());
            // Retrieve and set the field entity based on fieldCode in cropDTO
            Field field = fieldDAO.findById(cropDTO.getFieldCode())
                    .orElseThrow(() -> new DataPersistFailedException("field not found with ID: " + cropDTO.getFieldCode()));
            crop.setField(field);
            fieldDAO.save(field);
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<Crop> findId = cropDAO.findById(cropCode);
        if (!findId.isPresent()){
            throw new CropNotFoundException("Crop Details Delete not found!!");
        }else {
            cropDAO.deleteById(cropCode);
        }
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if (cropDAO.existsById(cropCode)){
            Crop crop = cropDAO.getReferenceById(cropCode);
            CropDTO cropDTO = mapping.convertToCropDTO(crop);
            cropDTO.setCropCommonName(cropDTO.getCropCommonName());
            if (crop.getField() != null){
                cropDTO.setFieldCode(crop.getField().getFieldCode());
            }else {
                cropDTO.setFieldCode(null);
            }
            return cropDTO;
        }else {
            return new CropErrorResponse(0,"Crop not found!!");
        }
    }



    @Override
    public List<CropDTO> getAllCrop() {
        List<Crop> crops = cropDAO.findAll();
        List<CropDTO> cropDTOS = new ArrayList<>();
        for (Crop crop : crops){
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(crop.getCropCode());
            cropDTO.setCropCommonName(crop.getCropCommonName());
            cropDTO.setCropScientificName(crop.getCropScientificName());
            cropDTO.setCropImage(crop.getCropImage());
            cropDTO.setCategory(crop.getCategory());
            cropDTO.setCropSeason(crop.getCropSeason());
            if (crop.getField() != null){
                cropDTO.setFieldCode(crop.getField().getFieldCode());
            }else {
                cropDTO.setFieldCode(null);
            }
            cropDTOS.add(cropDTO);

        }
        return cropDTOS;
    }
}
