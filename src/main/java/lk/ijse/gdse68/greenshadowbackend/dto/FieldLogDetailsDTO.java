package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FieldLogDetailsDTO implements FieldLogDetailsResponse,SuperDTO {
    @NotNull(message = "Field details cannot be null")
    private Field field;
    @NotNull(message = "Log details cannot be null")
    private Log log;
    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;
    @Max(value = 100, message = "Work fields count cannot exceed 100")
    private int workFieldsCount;
    @Getter
    private Date logDate;

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}
