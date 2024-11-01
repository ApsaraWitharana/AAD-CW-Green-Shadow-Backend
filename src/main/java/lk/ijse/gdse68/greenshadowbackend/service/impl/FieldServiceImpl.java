package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.service.FieldService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Override
    public void saveField(FieldDTO fieldDTO) {

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
