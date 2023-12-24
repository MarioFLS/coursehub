package com.coursehub.application.infra.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    @NotBlank(message = "O nome não pode ser vazio.")
    public String name;

    @NotNull(message = "O email não pode ser vazio.")
    @Email(message = "Precisa ser um email válido")
    public String email;


    @NotBlank(message = "O telefone não pode ser vazio.")
    public String telephone;

    public UserDTO() {}

    public UserDTO(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }
}
