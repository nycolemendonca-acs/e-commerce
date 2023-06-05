package com.santana.java.back.end.model;

import com.santana.java.back.end.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone_number;
    private LocalDateTime registration_data;

    // Converting instances User class -> UserDTO class
    public static User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setCpf(userDTO.getCpf());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPhone_number(userDTO.getPhone_number());
        user.setRegistration_data(userDTO.getRegistration_data());
        return user;
    }
}
