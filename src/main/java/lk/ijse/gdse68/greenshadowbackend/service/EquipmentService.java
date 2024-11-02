package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.CropResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(String id,EquipmentDTO equipmentDTO);
    void deleteEquipment(String id);
    EquipmentResponse getSelectedEquipment(String id);

    List<EquipmentDTO> getAllEquipment();
}
