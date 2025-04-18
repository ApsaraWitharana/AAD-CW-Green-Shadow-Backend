package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentDAO extends JpaRepository<Equipment,String> {
    List<Equipment> findByNameContainingIgnoreCase(String name);
    @Query("SELECT e.id from Equipment e order by  e.id DESC limit 1")
    String findLastEquipmentCode();
}
