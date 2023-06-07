package com.santana.java.back.end.converter;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User;

public class DTOConverter {
    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setKey(user.getKey());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone_number(user.getPhone_number());
        userDTO.setRegistration_data(user.getRegistration_date());
        return userDTO;
    }
}
