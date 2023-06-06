package com.santana.java.back.end.dto;

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
    @NotBlank(message = "Email is required")
    private String email;
    private String phone_number;
    private LocalDateTime registration_data;
}
