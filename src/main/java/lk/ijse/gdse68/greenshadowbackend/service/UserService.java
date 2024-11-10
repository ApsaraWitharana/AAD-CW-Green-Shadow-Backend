package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.UserDTO;

public interface UserService {

    int saveUser(UserDTO userDTO);

    UserDTO searchUser(String userName);


}
