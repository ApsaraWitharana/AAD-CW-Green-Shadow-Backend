package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.StaffFieldDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@Repository
public interface StaffFieldDetailsDAO extends JpaRepository<StaffFieldDetails,String> {
}
