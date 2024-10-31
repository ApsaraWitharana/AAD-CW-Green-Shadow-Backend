package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleResponse;
import lk.ijse.gdse68.greenshadowbackend.dao.VehicleDAO;
import lk.ijse.gdse68.greenshadowbackend.dto.VehicleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VehicleDAO vehicleDAO;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {

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
