package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author : sachini
 * @date : 2024-11-02
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentErrorResponse implements EquipmentResponse{
    private int errorCode;
    private String errorMassage;
}
