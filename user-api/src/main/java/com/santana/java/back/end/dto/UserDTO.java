package com.santana.java.back.end.dto;

// Um construtor vazio e um construtor com todos os argumentos da classe
import jakarta.annotation.PostConstruct;
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
    - Possui apenas os atributos da classe User */
public class UserDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
}


