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
public interface StaffFieldDetailsDAO extends JpaRepository<StaffFieldDetails,String> {

    // Custom query to check if a specific staff is already assigned to a field
    @Query("SELECT COUNT(sfd) FROM StaffFieldDetails sfd WHERE sfd.staff.id = ?1 AND sfd.field.fieldCode = ?2")
    long countByStaffAndField(String staffId, String fieldCode);

    // Custom query to get the next available ID or any other custom query logic if necessary
    @Query("SELECT MAX(sfd.sf_id) FROM StaffFieldDetails sfd")
    Long getNextId();
}
