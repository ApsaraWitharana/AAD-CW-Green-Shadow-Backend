package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.CropResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.CropDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.CropService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(cropDTO.getCropCode());
        Crop saveCrop = cropDAO.save(mapping.convertToCropEntity(cropDTO));
        if (saveCrop == null){
            throw new DataPersistFailedException("Crop cannot data saved!!");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {

    }

    @Override
    public void deleteCrop(String cropCode) {

    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        return null;
    }

    @Override
    public List<CropDTO> getAllCrop() {
        return null;
    }
}
