package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.FieldService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldDAO fieldDAO;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(fieldDTO.getFieldCode());
        var Field = mapping.convertToEntity(fieldDTO);
        var saveField = fieldDAO.save(Field);
        System.out.println(fieldDTO);

        if (saveField == null){
            throw new DataPersistFailedException("Field save not found!!");
        }
    }

    @Override
    public void updateField(String id, FieldDTO fieldDTO) {

    }

    @Override
    public void deleteField(String fieldCode) {

    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        return null;
    }

    @Override
    public List<FieldDTO> getAllField() {
        return null;
    }
}
