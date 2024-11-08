package lk.ijse.gdse68.greenshadowbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "log_field_details")
public class FieldLogDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @ManyToOne
    @JoinColumn(name = "field_code",referencedColumnName = "field_code",nullable = false)
    private Field field;
    @ManyToOne
    @JoinColumn(name = "log_code",referencedColumnName = "log_code",nullable = false)
    private Log log;
    private String description;
    private int work_field_count;
    private Date date;

}
