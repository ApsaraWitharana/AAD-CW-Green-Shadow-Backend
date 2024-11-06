package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements SuperDTO {
    @NotNull(message = "Log code is mandatory")
    @Pattern(regexp = "^LOG-\\d{3}$", message = "Log code must be in the format 'LOG-001'")
    private String logCode;

    @NotNull(message = "Date is required.")
    private Date logDate;

    @NotNull(message = "Log details is required.")
    @Size(max = 500, message = "Log details must not exceed 500 characters")
    private String logDetails;

    private String observedImage;
    @NotBlank(message = "Crop code is required.")
    private CropDTO cropCode;

}
