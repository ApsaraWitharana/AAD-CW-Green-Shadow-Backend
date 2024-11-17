package lk.ijse.gdse68.greenshadowbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
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
public class StaffLogDetailsDTO implements StaffLogDetailsResponse, Serializable {
    @NotNull(message = "Staff details cannot be null")
    private Staff staff;

    @NotNull(message = "Log details cannot be null")
    private Log log;

    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;

    @Getter
    @Max(value = 100, message = "Work staff count cannot exceed 100")
    private int workStaffCount;

    public void setWorkStaffCount(int workStaffCount) {
        this.workStaffCount = workStaffCount;
    }

    private Date logDate;
}
