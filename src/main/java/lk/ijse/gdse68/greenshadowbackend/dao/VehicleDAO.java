package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-31
 **/
@Repository
public interface VehicleDAO extends JpaRepository<Vehicle,String> {
    List<Vehicle> findByVehicleCategoryContainingIgnoreCase(String vehicleCategory);

    @Query("SELECT v.vehicleCode from Vehicle v order by v.vehicleCode DESC limit 1")
    String findLastVehicleCode();
}
