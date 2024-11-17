package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffLogDetailsDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;

import java.util.List;

public interface StaffLogDetailsService {
    void saveStaffLogDetails(LogDTO logDTO);

    StaffLogDetailsResponse getStaffLogDetailsById(String id);

    List<StaffLogDetailsDTO> getAllStaffLogDetails();

    String generateSLogCode();

}
