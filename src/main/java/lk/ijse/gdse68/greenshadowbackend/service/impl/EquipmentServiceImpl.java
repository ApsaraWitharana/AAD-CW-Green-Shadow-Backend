package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.EquipmentDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.FieldDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.EquipmentDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Equipment;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.EquipmentNotFoundException;
import lk.ijse.gdse68.greenshadowbackend.exception.FieldNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.exception.StaffNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.EquipmentService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private FieldDAO fieldDAO;
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
        Optional<Equipment> tmpEquipmentEntity = equipmentDAO.findById(id);
        if (!tmpEquipmentEntity.isPresent()){
            throw new EquipmentNotFoundException("Equipment update not found!!");
            }else {
            Equipment equipment = tmpEquipmentEntity.get();
            equipment.setId(equipmentDTO.getId());
            equipment.setName(equipmentDTO.getName());
            equipment.setType(equipmentDTO.getType());
            equipment.setStatus(equipmentDTO.getStatus());
            // Retrieve and set Field based on fieldCode
            if (equipmentDTO.getFieldCode() != null) {
                Field field = fieldDAO.findById(equipmentDTO.getFieldCode())
                        .orElseThrow(() -> new FieldNoteFoundException("Field with code " + equipmentDTO.getFieldCode() + " not found"));
                equipment.setField(field);
            }
            // Retrieve and set Staff based on staffId
            if (equipmentDTO.getStaffId() != null) {
                Staff staff = staffDAO.findById(equipmentDTO.getStaffId())
                        .orElseThrow(() -> new StaffNoteFoundException("Staff with ID " + equipmentDTO.getStaffId() + " not found"));
                equipment.setStaff(staff);
            }
            // Save updated Equipment
            equipmentDAO.save(equipment);
        }
    }

    @Override
    public void deleteEquipment(String id) {
        Optional<Equipment> findId = equipmentDAO.findById(id);
        if (!findId.isPresent()){
            throw new EquipmentNotFoundException("Equipment not found!!");
        }else {
            equipmentDAO.deleteById(id);
        }
    }

    @Override
    public EquipmentResponse getSelectedEquipment(String id) {
        if (equipmentDAO.existsById(id)){
            Equipment equipment = equipmentDAO.getReferenceById(id);
            EquipmentDTO equipmentDTO = mapping.convertToEquipmentDTO(equipment);
            equipmentDTO.setName(equipmentDTO.getName());
            return equipmentDTO;
        }else {
            return new EquipmentErrorResponse(0,"Equipments not found!!");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
       List<Equipment> equipments = equipmentDAO.findAll();
       List<EquipmentDTO> equipmentDTOS = new ArrayList<>();
       for (Equipment equipment : equipments){
           EquipmentDTO equipmentDTO =new EquipmentDTO();
           equipmentDTO.setId(equipment.getId());
           equipmentDTO.setName(equipment.getName());
           equipmentDTO.setType(equipment.getType());
           equipmentDTO.setStatus(equipment.getStatus());
           // Set staff ID if Staff entity is assigned to 'usedBy'
           if (equipment.getField() != null) {
               equipmentDTO.setFieldCode(equipment.getField().getFieldCode()); // Set the ID of the Staff entity
           } else {
               equipmentDTO.setFieldCode(null); // Or handle this based on your requirements if no staff is assigned
           }
           if (equipment.getStaff() !=null){
               equipmentDTO.setStaffId(equipment.getStaff().getId());
           }else {
               equipmentDTO.setStaffId(null);
           }
           equipmentDTOS.add(equipmentDTO);
       }
       return equipmentDTOS;
    }
}
