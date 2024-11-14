package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.CropDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.StaffNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.StaffService;
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
public class StaffServiceImpl implements StaffService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffDAO staffDAO;



    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setId(staffDTO.getId());
        var Staff =mapping.convertToEntity(staffDTO);
        var saveStaff = staffDAO.save(Staff);
        System.out.println(staffDTO);

        if (saveStaff == null){
            throw new DataPersistFailedException("Staff Member Save Note Found!");
        }
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<Staff> tmpStaffEntity = staffDAO.findById(id);
        if (!tmpStaffEntity.isPresent()){
            throw new StaffNoteFoundException("Staff update not found!");
        }else {
            Staff staff = tmpStaffEntity.get();
            staff.setFirstName(staffDTO.getFirstName());
            staff.setLastName(staffDTO.getLastName());
            staff.setDesignation(staffDTO.getDesignation());
            staff.setGender(staffDTO.getGender());
            staff.setJoinedDate(staffDTO.getJoinedDate());
            staff.setDob(staffDTO.getDob());
            staff.setAddressLine1(staffDTO.getAddressLine1());
            staff.setAddressLine2(staffDTO.getAddressLine2());
            staff.setAddressLine3(staffDTO.getAddressLine3());
            staff.setAddressLine4(staffDTO.getAddressLine4());
            staff.setContactNo(staffDTO.getContactNo());
            staff.setEmail(staffDTO.getEmail());
            staff.setRole(staffDTO.getRole());
            staffDAO.save(staff); // saving to the update entity details
        }
    }

    @Override
    public void deleteStaff(String id) {

        Optional<Staff>findId = staffDAO.findById(id);
        if (!findId.isPresent()){
            throw new StaffNoteFoundException("Staff not found!");
        }else {
            // If the customer is found, proceed with the deletion
            staffDAO.deleteById(id);
        }
    }

    @Override
    public StaffResponse getSelectedStaff(String id) {
       if (staffDAO.existsById(id)){
           Staff staff = staffDAO.getReferenceById(id);
           StaffDTO staffDTO = mapping.convertToDTO(staff);
           staffDTO.setFirstName(staffDTO.getFirstName());
           return  staffDTO;
       }else {
           return new StaffErrorResponse(0,"Staff Member not found!!");
       }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
       List<Staff> staffs = staffDAO.findAll();
       List<StaffDTO> staffDTOS = new ArrayList<>();
       for (Staff staff : staffs){
           StaffDTO staffDTO = new StaffDTO();
           staffDTO.setId(staff.getId());
           staffDTO.setFirstName(staff.getFirstName());
           staffDTO.setLastName(staff.getLastName());
           staffDTO.setDesignation(staff.getDesignation());
           staffDTO.setGender(staff.getGender());
           staffDTO.setJoinedDate(staff.getJoinedDate());
           staffDTO.setDob(staff.getDob());
           staffDTO.setAddressLine1(staff.getAddressLine1());
           staffDTO.setAddressLine2(staff.getAddressLine2());
           staffDTO.setAddressLine3(staff.getAddressLine3());
           staffDTO.setAddressLine4(staff.getAddressLine4());
           staffDTO.setContactNo(staff.getContactNo());
           staffDTO.setEmail(staff.getEmail());
           staffDTO.setRole(staff.getRole());
           staffDTOS.add(staffDTO);
       }
       return staffDTOS;
    }

}
