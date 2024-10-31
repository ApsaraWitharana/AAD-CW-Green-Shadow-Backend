package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.StaffDAO;
import lk.ijse.gdse68.greenshadowbackend.dao.VehicleDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.VehicleDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.entity.Vehicle;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.VehicleNotFound;
import lk.ijse.gdse68.greenshadowbackend.service.VehicleService;


import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        Optional<Vehicle> tmpVehicleEntity = vehicleDAO.findById(vehicleCode);
        if (!tmpVehicleEntity.isPresent()){
            throw new VehicleNotFound("Vehicle with code " + vehicleCode + " not found for update.");
        }else {
            Vehicle vehicle = tmpVehicleEntity.get();
            vehicle.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            vehicle.setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicle.setFuelType(vehicleDTO.getFuelType());
            vehicle.setStatus(vehicleDTO.getStatus());

            // Retrieve and set the Staff entity based on staffId in vehicleDTO
            Staff staff = staffDAO.findById(vehicleDTO.getStaffId())
                    .orElseThrow(() -> new DataPersistFailedException("Staff not found with ID: " + vehicleDTO.getStaffId()));
            vehicle.setUsedBy(staff);
            vehicle.setRemarks(vehicleDTO.getRemarks());
            // Save updated vehicle entity
            vehicleDAO.save(vehicle);
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {

        Optional<Vehicle> findId = vehicleDAO.findById(vehicleCode);
        if (!findId.isPresent()){
            throw new VehicleNotFound("Vehicle delete not found!!");
        }else {
            vehicleDAO.deleteById(vehicleCode);
        }
    }

    @Override
    public VehicleResponse getSelectedVehicleId(String vehicleCode) {
       if (vehicleDAO.existsById(vehicleCode)){
           Vehicle vehicle = vehicleDAO.getReferenceById(vehicleCode);
           //System.out.println("Vehicle:"+vehicle);
           VehicleDTO vehicleDTO = mapping.convertToDTO(vehicle);
           // Set the license plate number
           vehicleDTO.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
           // Set the staff ID if a Staff entity is assigned to 'usedBy'
           if (vehicle.getUsedBy() != null) {
               vehicleDTO.setStaffId(vehicle.getUsedBy().getId()); // Retrieve the staff ID from the Staff entity
           } else {
               vehicleDTO.setStaffId(null); // Or handle this based on your requirements if staff is not assigned
           }
           return vehicleDTO;
       }else {
           return new VehicleErrorResponse(0,"Vehicle not found!!");
       }
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return null;
    }
}
