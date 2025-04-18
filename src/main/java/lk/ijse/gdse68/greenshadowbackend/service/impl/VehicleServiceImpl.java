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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String generateNextVehicleCode() {
        String currentVehicleCode = vehicleDAO.findLastVehicleCode();
        String  numericPart = currentVehicleCode.replaceAll("[^0-9]", "");
        int nextCode = Integer.parseInt(numericPart)+1;
        return "VEH-"+String.format("%03d",nextCode);
    }

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
        List<Vehicle> vehicles = vehicleDAO.findAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setVehicleCode(vehicle.getVehicleCode());
            vehicleDTO.setLicensePlateNumber(vehicle.getLicensePlateNumber());
            vehicleDTO.setVehicleCategory(vehicle.getVehicleCategory());
            vehicleDTO.setFuelType(vehicle.getFuelType());
            vehicleDTO.setStatus(vehicle.getStatus());
            // Set staff ID if Staff entity is assigned to 'usedBy'
            if (vehicle.getUsedBy() != null) {
                vehicleDTO.setStaffId(vehicle.getUsedBy().getId()); // Set the ID of the Staff entity
            } else {
                vehicleDTO.setStaffId(null); // Or handle this based on your requirements if no staff is assigned
            }

            vehicleDTO.setRemarks(vehicle.getRemarks());
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }

    @Override
    public List<VehicleResponse> getVehicleByVehicleCategory(String vehicleCategory) {
        List<Vehicle> vehicleList = vehicleDAO.findByVehicleCategoryContainingIgnoreCase(vehicleCategory);
        return vehicleList.stream()
                .map(vehicle -> mapping.convertToDTO(vehicle))
                .collect(Collectors.toList());
    }
}
