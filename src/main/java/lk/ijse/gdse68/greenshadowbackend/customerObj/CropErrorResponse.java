package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : sachini
 * @date : 2024-11-01
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropErrorResponse  implements CropResponse{
    private int errorCode;
    private String errorMassage;
}
