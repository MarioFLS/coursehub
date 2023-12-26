package com.coursehub.application.domain.DTO;

import com.coursehub.application.infra.annotation.NotBlankIfNotNull;
import jakarta.validation.constraints.*;

import java.util.Optional;

public class UserUpdateDTO {
    @NotBlankIfNotNull(message = "O nome não pode ser vazio.")
    public Optional<String> name;
    @NotBlankIfNotNull(message = "O telefone não pode ser vazia.")
    public Optional<String> telephone;
    @NotBlankIfNotNull(message = "A senha não pode ser vazia.")
    @Min(value = 6, message = "A senha precisa ter no mínimo 6 caracteres.")
    public Optional<String> password;

    public UserUpdateDTO() {}

    public UserUpdateDTO(Optional<String> name, Optional<String> telephone, Optional<String> password) {
        this.name = name;
        this.telephone = telephone;
        this.password = password;
    }
}
