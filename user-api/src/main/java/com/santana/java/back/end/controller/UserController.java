// Chama a camada de serviço do user

package com.santana.java.back.end.controller;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User;
import com.santana.java.back.end.service.UserService;
// import com.santana.java.back.end.exception.UserNotFoundException;

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
@RequestMapping("/user") // route
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    // Retorna todos os users
    @GetMapping
    public List<UserDTO> getUser() { return userService.getAll(); }

    // Retorna um user por ID
    @GetMapping("/{id}")
    public  UserDTO findById(@PathVariable Long id) { return userService.findById(id); }

    // Criação de novo user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) { return userService.save(userDTO); }

    // Retorna um user com base no CPF
    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@PathVariable String cpf) { return userService.findByCpf(cpf); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws UserPrincipalNotFoundException { userService.delete(id); }

    // @RequestParam -> Passa parâmetros na URL para a rota
    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome) { return userService.queryByName(nome); }

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

    // Retorna um objeto com todos os usuários
    @GetMapping
    public List<UserDTO> getUsers() {
        return usuarios;
    }

    // Retorna um usuário utilizando o CPF como filtro
    @GetMapping("/{cpf}")
    public UserDTO getUsersFiltro(@PathVariable String cpf) {
        return usuarios
                .stream()
                .filter(userDTO -> userDTO.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    // Adicionar novo usuário
    // @Valid indica para o Spring que as validações que estão definidas no DTO devem ser realizadas antes da execução do método controlador
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO inserir(@RequestBody @Valid UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        return userDTO;
    }

    // Deleta um usuário e tem como parâmetro o CPF
    @DeleteMapping
    public boolean remover(@PathVariable String cpf) {
        return usuarios.removeIf(userDTO -> userDTO.getCpf().equals(cpf));
    }

    // Edição de users
    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

    public Page<UserDTO> getUsersPage(Pageable pageable) {
        return userService.getAllPage(pageable);
    }
}


