package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropDAO extends JpaRepository<Crop,String> {
    List<Crop> findByCropCommonNameContainingIgnoreCase(String cropCommonName);

    @Query("SELECT c.cropCode FROM Crop c ORDER BY c.cropCode DESC LIMIT 1")
    String findLastCropCode();
}
