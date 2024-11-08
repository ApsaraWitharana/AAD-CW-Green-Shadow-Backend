package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lk.ijse.gdse68.greenshadowbackend.customerObj.LogResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-06
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LogDTO implements LogResponse,SuperDTO {
    @NotNull(message = "Log code is mandatory")
    @Pattern(regexp = "^LOG-\\d{3}$", message = "Log code must be in the format 'LOG-001'")
    private String logCode;

    @NotNull(message = "Date is required.")
    private Date logDate;

    @NotNull(message = "Log details is required.")
    @Size(max = 500, message = "Log details must not exceed 500 characters")
    private String logDetails;

    private String observedImage;
    @Getter
    @NotBlank(message = "Crop code is required.")
    private String cropCode;

    @Getter
    private List<FieldLogDetailsDTO> fieldLogDetailsDTOS;
    private List<FieldDTO> fields;  // List of crops associated with the field

    private List<StaffLogDetailsDTO> staffLogDetailsDTOS;
    private List<StaffDTO> staff;
    public void setFieldLogDetailsDTOS(List<FieldLogDetailsDTO> fieldLogDetailsDTOS) {
        this.fieldLogDetailsDTOS = fieldLogDetailsDTOS;
    }
    public void setCropCode(String cropCode) {
        this.cropCode = cropCode;
    }

    @Override
    public void setLogImage(MultipartFile image) {
        String[] base64Images = AppUtil.toBase64Images(image);
        this.observedImage = base64Images[0];
    }

}
