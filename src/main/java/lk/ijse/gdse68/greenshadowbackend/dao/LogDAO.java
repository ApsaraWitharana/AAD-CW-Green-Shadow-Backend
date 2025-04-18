package lk.ijse.gdse68.greenshadowbackend.dao;

import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-06
 **/
@Repository
public interface LogDAO extends JpaRepository<Log,String> {
    List<Log> findByLogCodeContainingIgnoreCase(String logCode);
    @Query("SELECT l.logCode from Log  l order by  l.logCode DESC limit 1")
    String findLastLogCode();
}
