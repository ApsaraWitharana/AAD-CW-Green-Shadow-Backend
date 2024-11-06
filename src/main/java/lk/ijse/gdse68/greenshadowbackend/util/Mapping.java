package lk.ijse.gdse68.greenshadowbackend.util;

import lk.ijse.gdse68.greenshadowbackend.dto.*;
import lk.ijse.gdse68.greenshadowbackend.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public StaffDTO convertToDTO(Staff staff){
        return modelMapper.map(staff,StaffDTO.class);
    }
    public Staff convertToEntity(StaffDTO staffDTO){
        return modelMapper.map(staffDTO,Staff.class);
    }

    public VehicleDTO convertToDTO(Vehicle vehicle){
        return modelMapper.map(vehicle,VehicleDTO.class);
    }
    public Vehicle convertToEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO,Vehicle.class);
    }

    public FieldDTO convertToDTO(Field field){
        return modelMapper.map(field, FieldDTO.class);
    }
    public Field convertToEntity(FieldDTO fieldDTO){
        return modelMapper.map(fieldDTO,Field.class);
    }

    public CropDTO convertToCropDTO(Crop crop){
        return modelMapper.map(crop, CropDTO.class);
    }
    public Crop convertToCropEntity(CropDTO cropDTO){
        return modelMapper.map(cropDTO, Crop.class);
    }

    public EquipmentDTO convertToEquipmentDTO(Equipment equipment){
        return modelMapper.map(equipment, EquipmentDTO.class);
    }
    public Equipment convertToEquipmentEntity(EquipmentDTO equipmentDTO){
        return modelMapper.map(equipmentDTO,Equipment.class);
    }

    public LogDTO convertToLogDTO(Log log){
        return  modelMapper.map(log, LogDTO.class);
    }

    public Log convertToLogEntity(LogDTO logDTO){
        return modelMapper.map(logDTO, Log.class);
    }
}
