package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.VehicleDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.VehicleDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.Vehicle;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.service.VehicleService;


import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private Mapping mapping;
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(vehicleDTO.getVehicleCode());
        var vehicle = mapping.convertToEntity(vehicleDTO);
        Staff staff = staffDAO.findById(vehicleDTO.getStaffId())
                .orElseThrow(() -> new DataPersistFailedException("Staff not found with ID: " + vehicleDTO.getStaffId()));
        vehicle.setUsedBy(staff);
        var savedVehicle = vehicleDAO.save(vehicle);
        System.out.println(vehicleDTO);
        if (savedVehicle == null){
            throw new DataPersistFailedException("Vehicle save not found!!");
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {

    }

    @Override
    public void deleteVehicle(String vehicleCode) {

    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleCode) {
        return null;
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return null;
    }
}