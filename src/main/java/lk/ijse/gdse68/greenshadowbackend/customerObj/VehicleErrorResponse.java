package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author : sachini
 * @date : 2024-10-31
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleErrorResponse {
    private int errorCode;
    private String errorMassage;
}
