package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StaffLogDetailsDTO implements StaffLogDetailsResponse, Serializable {
    private Staff staff;
    private Log log;
    private String description;
    private int work_staff_count;
    private Date date;
}
