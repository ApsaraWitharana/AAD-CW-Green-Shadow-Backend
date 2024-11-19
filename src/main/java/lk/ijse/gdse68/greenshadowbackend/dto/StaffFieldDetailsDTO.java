package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffFieldDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lombok.*;

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
public class StaffFieldDetailsDTO implements StaffFieldDetailsResponse, Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sf_id; // Instead of Long
    @NotNull(message = "Staff details cannot be null")
    private Staff staff;
    private String firstName;
    @NotNull(message = "Field details cannot be null")
    private Field field;
    private String status;
    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;
    @Max(value = 100, message = "Work staff count cannot exceed 100")
    private int workStaffCount;
    private Date date;
}
