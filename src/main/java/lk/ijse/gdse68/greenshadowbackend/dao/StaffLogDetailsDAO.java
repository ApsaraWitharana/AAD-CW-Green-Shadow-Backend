package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.StaffLogDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@Repository
public interface StaffLogDetailsDAO extends JpaRepository<StaffLogDetails,Long> {
    @Query("SELECT MAX(sld.sl_id) FROM StaffLogDetails sld")
    Long getNextId();

    @Query(value = "SELECT s.sl_id, s.log_date, s.description, s.work_staff_count, s.first_name " +
            "FROM staff_log_details s", nativeQuery = true)
    List<Object[]> findAllStaffLogDetailsNative();
}
