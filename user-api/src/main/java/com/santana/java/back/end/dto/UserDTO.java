package com.santana.java.back.end.dto;

import com.santana.java.back.end.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "CPF is required")
    private String cpf;
    private String address;
    @NotBlank(message = "E-mail is required")
    private String email;
    private String phone_number;
    private LocalDateTime registration_data;

    // Converting instances UserDTO class -> User class
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
