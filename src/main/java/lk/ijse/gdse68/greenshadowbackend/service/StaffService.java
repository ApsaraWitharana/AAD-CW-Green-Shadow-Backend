package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-30
 **/
public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void updateStaff(String id,StaffDTO staffDTO);
    void deleteStaff(String id);
    StaffResponse getSelectedStaff(String id);

    List<StaffDTO> getAllStaff();
    List<StaffResponse> getStaffByFirstName(String firstName);
}
