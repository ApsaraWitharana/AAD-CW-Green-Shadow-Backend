package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * @author : sachini
 * @date : 2024-11-08
 **/
public class FieldLogDetailsErrorResponse implements FieldLogDetailsResponse, Serializable {
    private int errorCode;
    private String errorMassage;
}