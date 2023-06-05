package com.santana.java.back.end.repository;

import com.santana.java.back.end.dto.UserDTO;
import com.santana.java.back.end.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
    List<User> queryByNameLike(String name);
}
