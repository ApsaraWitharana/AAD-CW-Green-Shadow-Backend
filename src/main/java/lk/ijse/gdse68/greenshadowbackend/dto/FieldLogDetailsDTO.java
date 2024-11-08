package lk.ijse.gdse68.greenshadowbackend.dto;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldLogDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FieldLogDetailsDTO implements FieldLogDetailsResponse,SuperDTO {
    private Field field;
    private Log log;
    private String description;
    private int work_fields_count;
    private Date date;
}
