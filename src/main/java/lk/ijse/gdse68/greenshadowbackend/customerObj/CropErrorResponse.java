package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Override
    public void setCropImage(MultipartFile image) {

    }

    @Override
    public String encodeImageToBase64(MultipartFile image) throws IOException {
        return null;
    }
}
