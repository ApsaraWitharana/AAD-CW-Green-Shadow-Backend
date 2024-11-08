package lk.ijse.gdse68.greenshadowbackend.dto;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldLogDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldLogDetailsDTO implements FieldLogDetailsResponse,SuperDTO {
    private String id;
    private String log_code;
    private String field_code;
    private String description;
    private int work_fields_count;
    private Date date;
}
