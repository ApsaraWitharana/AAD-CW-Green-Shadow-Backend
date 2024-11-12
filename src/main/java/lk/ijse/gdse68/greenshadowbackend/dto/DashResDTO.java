package lk.ijse.gdse68.greenshadowbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DashResDTO {
    private HttpStatus code;
    private String message;
    private Object data;
}
