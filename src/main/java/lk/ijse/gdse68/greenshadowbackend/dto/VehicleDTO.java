package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse68.greenshadowbackend.customerObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
/**
 * @author : sachini
 * @date : 2024-10-31
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleResponse,SuperDTO{
    @NotBlank(message = "Vehicle code is required.")
    @Pattern(regexp = "^VEH-\\d{3}$", message = "Vehicle code must follow the format 'VEH-001'.")
    private String vehicleCode;

    @NotBlank(message = "License plate number is required.")
    private String licensePlateNumber;

    @NotBlank(message = "Vehicle category is required.")
    private String vehicleCategory;

    @NotBlank(message = "Fuel type is required.")
    private String fuelType;

    @NotBlank(message = "Status is required.")
    private String status; // Must be either "Available" or "Out of Service"

    @NotBlank(message = "Staff ID is required.")
    private String staffId; // Use IDs for staff assignment

    private String remarks;
}
