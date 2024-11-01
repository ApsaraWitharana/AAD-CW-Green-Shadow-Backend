package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.FieldNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.FieldService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
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
    public void updateField( FieldDTO fieldDTO) throws ClassNotFoundException {
        Optional<Field> tmpFieldEntity = fieldDAO.findById(fieldDTO.getFieldCode());
        if (!tmpFieldEntity.isPresent()){
            throw new ClassNotFoundException("Field Update not found!!");
        }else {
            Field field = tmpFieldEntity.get();
            field.setFieldName(fieldDTO.getFieldName());
            field.setFieldLocation(fieldDTO.getFieldLocation());
            field.setExtentSize(fieldDTO.getExtentSize());
            field.setFieldImage1(fieldDTO.getFieldImage1());
            field.setFieldImage2(fieldDTO.getFieldImage2());
            fieldDAO.save(field);
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<Field> selectFieldCode = fieldDAO.findById(fieldCode);
        if (selectFieldCode.isPresent()){
            fieldDAO.deleteById(fieldCode);
        }else {
            throw new FieldNoteFoundException("Field not found!!");
        }
    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
         if (fieldDAO.existsById(fieldCode)){
             Field field = fieldDAO.getReferenceById(fieldCode);
             FieldDTO fieldDTO = mapping.convertToDTO(field);
             fieldDTO.setFieldName(fieldDTO.getFieldName());
             return fieldDTO;
         }else {
             return new FieldErrorResponse(0,"Field not found!!");
         }
    }

    @Override
    public List<FieldDTO> getAllField() {
        List<Field> fields = fieldDAO.findAll();
        List<FieldDTO> fieldDTOS = new ArrayList<>();
        for (Field field : fields){
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(field.getFieldCode());
            fieldDTO.setFieldName(field.getFieldName());
            fieldDTO.setFieldLocation(field.getFieldLocation());
            fieldDTO.setExtentSize(field.getExtentSize());
            fieldDTO.setFieldImage1(field.getFieldImage1());
            fieldDTO.setFieldImage2(field.getFieldImage2());
            fieldDTOS.add(fieldDTO);
        }
        return fieldDTOS;
    }
}
