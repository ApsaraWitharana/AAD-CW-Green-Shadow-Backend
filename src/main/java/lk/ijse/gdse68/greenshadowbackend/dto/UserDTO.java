package lk.ijse.gdse68.greenshadowbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//02
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String role;
}
