package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffFieldDetailsErrorResponse implements StaffFieldDetailsResponse, Serializable {
    private int errorCode;
    private String errorMassage;
}
