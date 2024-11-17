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
@Table(name = "staff_log_details")
public class StaffLogDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sl_id;
    @ManyToOne
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id",nullable = false)
    private Staff staff;
    private String firstName;
    @ManyToOne
    @JoinColumn(name = "log_code",referencedColumnName = "log_code",nullable = false)
    private Log log;
    private String description;
    private int work_staff_count;
    private Date logDate;
}
