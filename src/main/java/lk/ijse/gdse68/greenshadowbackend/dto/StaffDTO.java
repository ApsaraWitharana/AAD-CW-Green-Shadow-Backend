package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Vehicle;
import lk.ijse.gdse68.greenshadowbackend.util.GenderEnum;
import lk.ijse.gdse68.greenshadowbackend.util.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-29
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffResponse,SuperDTO{
    @NotNull(message = "Staff ID is required.")
    @Pattern(regexp = "^STA-\\d{3}$", message = "Staff ID must follow the format 'STA-000'.")
    private String id;

    @NotNull(message = "First name is required.")
    @Size(min = 2, message = "First name must contain at least 2 characters.")
    private String firstName;

    @NotNull(message = "Last name is required.")
    @Size(min = 2, message = "Last name must contain at least 2 characters.")
    private String lastName;

    @NotNull(message = "Designation is required.")
    private String designation;

    @NotNull(message = "Gender selection is required.")
    private GenderEnum gender;

    @NotNull(message = "Join date is required.")
    private Date joinedDate;

    @NotNull(message = "Date of birth is required.")
    private Date dob;

    @NotNull(message = "Primary address line is required.")
    private String addressLine1;

    @NotNull(message = "Secondary address line is required.")
    private String addressLine2;

    private String addressLine3;
    private String addressLine4;
    private String addressLine5;

    @NotNull(message = "Contact number is required.")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Contact number must be between 10 and 15 digits, with an optional '+' prefix.")
    private String contactNo;

    @NotNull(message = "Email address is required.")
    @Email(message = "Email address must be valid.")
    private String email;

    @NotNull(message = "Role is required.")
    private RoleEnum role;


    private List<Vehicle> vehicles;

}
