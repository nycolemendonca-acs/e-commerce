package com.santana.java.back.end.controller;

import com.santana.java.back.end.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user") // route

public class UserController {
    public static List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua A");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("1234-3454");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua B");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("1234-3454");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua C");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("1234-3454");
        userDTO3.setDataCadastro(LocalDateTime.now());

        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return usuarios;
    }
    
    @GetMapping("/{cpf}")
    public UserDTO getUsersFiltro(@PathVariable String cpf) {
        return usuarios
                .stream()
                .filter(userDTO -> userDTO.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }
}


