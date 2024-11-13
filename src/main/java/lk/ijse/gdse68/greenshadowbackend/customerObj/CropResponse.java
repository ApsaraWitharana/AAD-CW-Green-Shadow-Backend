package lk.ijse.gdse68.greenshadowbackend.customerObj;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : sachini
 * @date : 2024-11-01
 **/
public interface CropResponse {
    // Method to set image using MultipartFile
    void setCropImage(MultipartFile image);

    String encodeImageToBase64(MultipartFile image) throws IOException;
}
