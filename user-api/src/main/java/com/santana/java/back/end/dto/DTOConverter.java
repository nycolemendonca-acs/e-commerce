package com.santana.java.back.end.dto;

import com.santana.java.back.end.model.User;
public class DTOConverter {
    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setCpf(user.getCpf());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone_number(user.getPhone_number());
        userDTO.setRegistration_data(user.getRegistration_data());
        return userDTO;
    }
}
