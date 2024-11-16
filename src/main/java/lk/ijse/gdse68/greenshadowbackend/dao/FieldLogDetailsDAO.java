package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.FieldLogDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldLogDetailsDAO extends JpaRepository<FieldLogDetails,Long> {
    @Query("SELECT COALESCE(MAX(l.id), 0) + 1 FROM FieldLogDetails l")
    Long getNextId();
}

