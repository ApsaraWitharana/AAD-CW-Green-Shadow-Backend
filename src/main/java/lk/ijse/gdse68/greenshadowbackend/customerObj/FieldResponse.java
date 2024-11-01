package lk.ijse.gdse68.greenshadowbackend.customerObj;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : sachini
 * @date : 2024-11-01
 **/
public interface FieldResponse {
    // Method to set images using MultipartFile
    void setFieldImages(MultipartFile image1, MultipartFile image2);
}
