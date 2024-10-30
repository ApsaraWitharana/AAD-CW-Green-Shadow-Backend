package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

    }

    @Override
    public void deleteStaff(String id) {

    }

    @Override
    public StaffResponse getSelectedStaff(String id) {
        return null;
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return null;
    }
}
