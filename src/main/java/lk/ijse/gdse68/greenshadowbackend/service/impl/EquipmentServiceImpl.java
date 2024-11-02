package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.EquipmentDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.EquipmentDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.EquipmentService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
           equipmentDTO.setId(equipmentDTO.getId());
        var Equipment = mapping.convertToEquipmentEntity(equipmentDTO);
        var saveEquipment = equipmentDAO.save(Equipment);
        System.out.println(equipmentDTO);
        if (saveEquipment == null){
            throw new DataPersistFailedException("Equipment save not found!!");
        }
    }

    @Override
    public void updateEquipment(String id, EquipmentDTO equipmentDTO) {

    }

    @Override
    public void deleteEquipment(String id) {

    }

    @Override
    public EquipmentResponse getSelectedEquipment(String id) {
        return null;
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return null;
    }
}
