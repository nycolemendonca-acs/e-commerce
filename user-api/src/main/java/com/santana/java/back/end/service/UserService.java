// MÉTODO CONVERT NÃO FUNCIONA [RESOLVER]

package com.santana.java.back.end.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User; // Aqui importei o arquivo que contém o método convert()
import com.santana.java.back.end.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /*  getAll()
    *   - Chama o método findAll, do UserRepository, que retorna uma lista de usuários,
    *   sendo instàncias da entidade User
    *   - Transforma uma lista em um stream e chama o método map para transformar uma
    *   lista de usuários em uma lista de DTOs.
    *   - Retorna a lista de DTOs. */
    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    // findById() busca um usuário por um ID específico
    public UserDTO findById(longer userId) {
        User usuario = userRepository
                        .findById(userId)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return UserDTO.convert(usuario);

    }

    // save() salva um usuário no DB
    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    // delete() exclui um usuário do DB
    public UserDTO delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return UserDTO.convert(user);
    }

    // findByCpf() busca um usuário pelo CPF
    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);

        if (user != null) return UserDTO.convert(user);
        return null;
    }

    // queryByName() faz uma busca pelo nome do usuário
    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    /*  editUser() recebe o ID e um objeto da classe UserDTO, e busca um usuário.
    *   Caso ele exista, os dados desse usuário serão atualizados com o que foi recebido no objeto.
    *   Caso contrário, será retornado um erro de que o usuário não foi encontrado. */
    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException());

        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) user.setEmail(userDTO.getEmail());
        if (userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())).user.setTelefone(userDTO.getTelefone());
        if (userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())).user.setEndereco(userDTO.getEndereco());

        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page) {
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
}
