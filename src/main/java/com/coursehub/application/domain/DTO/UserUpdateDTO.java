package com.coursehub.application.domain.DTO;

import com.coursehub.application.infra.annotation.NotBlankIfNotNull;

import java.util.Optional;

public class UserUpdateDTO {
    @NotBlankIfNotNull(message = "O nome não pode ser vazio.")
    public Optional<String> name;
    @NotBlankIfNotNull(message = "O telefone não pode ser vazia.")
    public Optional<String> telephone;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Optional<String> name, Optional<String> telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getTelephone() {
        return telephone;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public void setTelephone(Optional<String> telephone) {
        this.telephone = telephone;
    }

}
