package lk.ijse.gdse68.greenshadowbackend.service.impl;

import lk.ijse.gdse68.greenshadowbackend.dao.UserRepository;
import lk.ijse.gdse68.greenshadowbackend.dto.AuthDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.UserDTO;
import lk.ijse.gdse68.greenshadowbackend.entity.User;
import lk.ijse.gdse68.greenshadowbackend.service.UserService;
import lk.ijse.gdse68.greenshadowbackend.util.Mapping;
import lk.ijse.gdse68.greenshadowbackend.util.RoleEnum;
import lk.ijse.gdse68.greenshadowbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            AuthDTO authDTO = new AuthDTO();
            authDTO.setRole(String.valueOf(RoleEnum.valueOf(userDTO.getRole())));
//            userDTO.setRole(String.valueOf(RoleEnum.MANAGER));
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.Created;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String userName) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(userName);
        return modelMapper.map(user,UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String userName) {
        if (userRepository.existsByEmail(userName)){
            User user = userRepository.findByEmail(userName);
            return modelMapper.map(user,UserDTO.class);
        }else {
            return null;
        }
    }


}
