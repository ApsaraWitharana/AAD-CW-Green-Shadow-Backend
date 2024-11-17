package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.StaffLogDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@Repository
public interface StaffLogDetailsDAO extends JpaRepository<StaffLogDetails,Long> {
    @Query("SELECT MAX(sld.sl_id) FROM StaffLogDetails sld")
    Long getNextId();
}
