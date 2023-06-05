package com.santana.java.back.end.controller;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public static List<UserDTO> users = new ArrayList<UserDTO>();

    @PostConstruct
    public void initiateList() {
        UserDTO userDTO001 = new UserDTO();
        userDTO001.setName("Eduardo");
        userDTO001.setCpf("123");
        userDTO001.setAddress("Rua A");
        userDTO001.setEmail("eduardo@email.com");
        userDTO001.setPhone_number("1234-3454");
        userDTO001.setRegistration_data(LocalDateTime.now());

        UserDTO userDTO002 = new UserDTO();
        userDTO002.setName("Luiz");
        userDTO002.setCpf("456");
        userDTO002.setAddress("Rua B");
        userDTO002.setEmail("luiz@email.com");
        userDTO002.setPhone_number("1234-3454");
        userDTO002.setRegistration_data(LocalDateTime.now());

        UserDTO userDTO003 = new UserDTO();
        userDTO003.setName("Bruna");
        userDTO003.setCpf("678");
        userDTO003.setAddress("Rua C");
        userDTO003.setEmail("bruna@email.com");
        userDTO003.setPhone_number("1234-3454");
        userDTO003.setRegistration_data(LocalDateTime.now());

        users.add(userDTO001);
        users.add(userDTO002);
        users.add(userDTO003);
    }

    private final UserService userService;

    // Returns all users
    @GetMapping
    public List<UserDTO> getUsers() { return userService.getAll(); }

    // Returns a user based on ID
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) { return userService.findById(id); }

    // Returns a user based on CPF
    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf); // (cpf, key) -> key (error)
    }

    // Add user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    // Delete user
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws UserPrincipalNotFoundException {
        userService.delete(id);
    }

    // Search a user
    @GetMapping("/search")
    public List<UserDTO> queryByName(
            @RequestParam(name = "name", required = true) String name) {
                return userService.queryByName(name);
    }

    // Edit user
    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    // Pageable
    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable) {
        return userService.getAllPage(pageable);
    }
}
