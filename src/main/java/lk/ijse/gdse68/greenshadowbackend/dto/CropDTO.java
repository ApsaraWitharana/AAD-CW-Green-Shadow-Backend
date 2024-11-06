package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.CropResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Log;

import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropResponse,SuperDTO {
    @NotBlank(message = "Crop code is required")
    @Pattern(regexp = "^CRP-\\d{3}$", message = "Crop code must match the format 'CRP-001'")
    private String cropCode;  // e.g., CRP-001

    @NotBlank(message = "Crop common name is required")
    @Size(max = 100, message = "Crop common name must not exceed 100 characters")
    private String cropCommonName;

    @NotBlank(message = "Crop scientific name is required")
    @Size(max = 100, message = "Crop scientific name must not exceed 100 characters")
    private String cropScientificName;

    @Getter
    @NotBlank(message = "CropImage is required")
    private String cropImage;  // Optional image URL or base64 encoding

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;  // e.g., Cereal

    @NotBlank(message = "Crop season is required")
    @Size(max = 50, message = "Crop season must not exceed 50 characters")
    private String cropSeason;
    @NotBlank(message = "fieldCode is required")
    private String fieldCode;

    private List<Log> logs;
    public void setCropImage(String cropImage) { //set this as a save name to setter method and converting image method as a error runtime set to setter method fix it
        this.cropImage = cropImage;
    }

    // Method to set image using MultipartFile
    @Override
    public void setCropImage(MultipartFile image) {
        String[] base64Images = AppUtil.toBase64Images(image);
        this.cropImage = base64Images[0];
    }
}
