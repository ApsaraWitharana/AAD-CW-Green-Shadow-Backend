package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogErrorResponse implements LogResponse {
    private int errorCode;
    private String errorMassage;

    @Override
    public void setLogImage(MultipartFile images) {

    }
}
