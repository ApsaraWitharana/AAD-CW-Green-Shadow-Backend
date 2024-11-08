package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffLogDetailsErrorResponse implements StaffLogDetailsResponse, Serializable {
    private int errorCode;
    private String errorMassage;
}
