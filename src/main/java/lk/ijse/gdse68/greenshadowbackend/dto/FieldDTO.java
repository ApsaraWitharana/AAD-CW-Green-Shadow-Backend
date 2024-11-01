package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO {
    @NotBlank(message = "Field code is required")
    @Pattern(regexp = "^FED-\\d{3}$", message = "Field code must match the format 'FED-001'")
    private String fieldCode;  // e.g., FED-001

    @NotBlank(message = "Field name is required")
    @Size(max = 100, message = "Field name must not exceed 100 characters")
    private String fieldName;

    @NotBlank(message = "Field location is required")
    private String fieldLocation;

    @Positive(message = "Extent size must be positive")
    private double extentSize;

    private String fieldImage1;  // Optional image URL or base64 encoding

    private String fieldImage2;
}
