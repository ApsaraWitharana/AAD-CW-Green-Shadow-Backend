package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.StaffFieldDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@Repository
public interface StaffFieldDetailsDAO extends JpaRepository<StaffFieldDetails,Long> {

    // Custom query to check if a specific staff is already assigned to a field
    @Query("SELECT COUNT(sfd) FROM StaffFieldDetails sfd WHERE sfd.staff.id = ?1 AND sfd.field.fieldCode = ?2")
    long countByStaffAndField(String staffId, String fieldCode);

    // Custom query to get the next available ID or any other custom query logic if necessary
    @Query("SELECT MAX(sfd.sf_id) FROM StaffFieldDetails sfd")
    Long getNextId();
    @Query(value = "SELECT * FROM staff_field_details", nativeQuery = true)
    List<Object[]> findAllStaffFieldDetailsNative();

//    @Query(value = "SELECT sfd.sf_id, sfd.status, sfd.description, sfd.work_staff_count, sfd.date, sfd.first_name FROM StaffFieldDetails sfd ", nativeQuery = true)
//    List<Object[]> findAllStaffFieldDetailsNative();


}
