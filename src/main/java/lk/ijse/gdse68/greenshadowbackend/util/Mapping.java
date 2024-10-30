package lk.ijse.gdse68.greenshadowbackend.util;

import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
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
}
