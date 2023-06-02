package com.santana.java.back.end.dto;

import com.santana.java.back.end.model.User;
import jakarta.validation.constraints.NotBlank;
// Um construtor vazio e um construtor com todos os argumentos da classe
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
// Métodos Getter e Setter
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

/*  - Retorno dos métodos da camada Controller
    - Possui apenas os atributos da classe User
    - Notlank() faz a validação dos dados de entrada */
public class UserDTO {
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;
    private String endereco;
    @NotBlank(message = "E-mail é obrigatório.")
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setCpf(user.getCpf());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
}


