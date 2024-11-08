package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldResponse, SuperDTO {
    @NotBlank(message = "Field code is required")
    @Pattern(regexp = "^FED-\\d{3}$", message = "Field code must match the format 'FED-001'")
    private String fieldCode;  // e.g., FED-001

    @NotBlank(message = "Field name is required")
    @Size(max = 100, message = "Field name must not exceed 100 characters")
    private String fieldName;

    @NotBlank(message = "Field location is required")
    private String fieldLocation;

    @Positive(message = "Extent size must be positive")
    private Double extentSize;

    private String fieldImage1;  // Optional image URL or base64 encoding

    private String fieldImage2;

    private List<CropDTO> crops;  // List of crops associated with the field
    private List<EquipmentDTO> equipmentList;

    private List<StaffFieldDetailsDTO> staffFieldDetailsDTOS;
    private List<StaffDTO> staff;
     @Override
    // Method to set images using MultipartFile
    public void setFieldImages(MultipartFile image1, MultipartFile image2) {
        String[] base64Images = AppUtil.toBase64Images(image1, image2);
        this.fieldImage1 = base64Images[0];
        this.fieldImage2 = base64Images[1];
    }

}
