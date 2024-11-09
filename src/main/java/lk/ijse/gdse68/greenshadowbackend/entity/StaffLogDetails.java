package lk.ijse.gdse68.greenshadowbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StaffLogDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id",nullable = false)
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "log_code",referencedColumnName = "log_code",nullable = false)
    private Log log;
    private String description;
    private int work_staff_count;
    private Date date;
}
