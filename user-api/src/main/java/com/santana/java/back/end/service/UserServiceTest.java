package com.santana.java.back.end.service;

import com.santana.java.back.end.converter.DTOConverter;
import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User;
import com.santana.java.back.end.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(getUser(1, "User Name", "123"));
        users.add(getUser(1, "User Name 2", "321"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> usersReturn = userService.getAll();

        Assertions.assertEquals(2, usersReturn.size());
    }

    @Test
    public void testSaveUser() {
        User userDB = getUser(1, "User Name", "123");
        UserDTO userDTO = DTOConverter.convert(userDB);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDB);

        UserDTO user = userService.save(userDTO);
        Assertions.assertEquals("User Name", user.getName());
        Assertions.assertEquals("123", user.getCpf());
    }

    @Test
    public void testEditUser() {
        User userDB = getUser(1, "User Name", "123");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userDB));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDB);

        UserDTO userDTO = DTOConverter.convert(userDB);
        userDTO.setAddress("New address");
        userDTO.setPhone_number("1234");

        UserDTO user = userService.editUser(1L, userDTO);
        Assertions.assertEquals("New address", user.getAddress());
        Assertions.assertEquals("1234", user.getPhone_number());
    }

    public static User getUser(Integer id, String name, String cpf) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCpf(cpf);
        user.setAddress("adress");
        user.setPhone_number("5432");
        return user;
    }
}
