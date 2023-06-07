package com.santana.java.back.end.service;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User;
import com.santana.java.back.end.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // Instance of the class -> Created at application creation
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.convert(user);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());

        userDTO.setRegistration_data(LocalDateTime.now());

        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return UserDTO.convert(user);
    }

    public UserDTO findByCpfAndKey(String cpf, String key) {
        User user = userRepository.findByCpf(cpf, key);
        if (user != null) return UserDTO.convert(user);
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = userRepository.queryByNameLike(name);
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(RuntimeException::new);

        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) user.setEmail(userDTO.getEmail());
        if (userDTO.getPhone_number() != null && !user.getPhone_number().equals(userDTO.getPhone_number())) user.setPhone_number(userDTO.getPhone_number());
        if (userDTO.getAddress() != null && !user.getAddress().equals(userDTO.getAddress())) user.setAddress(userDTO.getAddress());

        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page) {
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
}
