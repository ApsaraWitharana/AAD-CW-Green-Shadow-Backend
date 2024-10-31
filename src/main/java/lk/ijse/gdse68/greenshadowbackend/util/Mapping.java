package lk.ijse.gdse68.greenshadowbackend.util;

import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.VehicleDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.Vehicle;
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
}
