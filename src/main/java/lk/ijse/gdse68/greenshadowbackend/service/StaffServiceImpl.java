package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.StaffNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
           staffDTO.setFirstName(staffDTO.getFirstName().split("")[0]);
           return  staffDTO;
       }else {
           return new StaffErrorResponse(0,"Staff Member not found!!");
       }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return null;
    }
}
