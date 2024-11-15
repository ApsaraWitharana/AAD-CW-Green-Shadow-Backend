package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-30
 **/
@Repository
public interface StaffDAO extends JpaRepository<Staff,String> {
    List<Staff> findByFirstNameContainingIgnoreCase(String firstName);


}
