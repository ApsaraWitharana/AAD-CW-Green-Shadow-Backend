package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropDAO extends JpaRepository<Crop,String> {
    List<Crop> findByCropCommonNameContainingIgnoreCase(String cropCommonName);
}
