package lk.ijse.gdse68.greenshadowbackend.entity;

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
public class StaffFieldDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sf_id;
    @ManyToOne
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id",nullable = false)
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "field_code",referencedColumnName = "field_code",nullable = false)
    private Field field;
    private String description;
    private int work_staff_count;
    private Date date;
}
