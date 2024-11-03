package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.EquipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentResponse,SuperDTO {
    @NotBlank(message = "Equipment id is required")
    @Pattern(regexp = "^EPT-\\d{3}$", message = "Crop code must match the format 'CRP-001'")
    private String id; // ID in formatted style "EPT-001"

    @NotBlank(message = "Equipment name is required")
    @Size(max = 100, message = "Equipment name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Type is required")
    @Size(max = 50, message = "Type must be less than 50 characters")
    private String type;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must be less than 20 characters")
    private String status;

    @Getter
    private String fieldCode; // Reference to Field's code
    @Getter
    private String staffId;


}

