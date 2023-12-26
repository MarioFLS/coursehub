package com.coursehub.application.domain.DTO;

import com.coursehub.application.infra.annotation.TaxIdentifier;
import jakarta.validation.constraints.*;

public class UserCreateDTO {
    @NotBlank(message = "O nome não pode ser vazio.")
    public String name;

    @NotNull(message = "O email não pode ser vazio.")
    @Email(message = "Precisa ser um email válido")
    public String email;

    @NotBlank(message = "O telefone não pode ser vazio.")
    public String telephone;

    @NotBlank(message = "A senha não pode ser vazia.")
    @Min(value = 6, message = "A senha precisa ter no mínimo 6 caracteres.")
    public String password;

    @NotBlank(message = "O CPF não pode ser vazio.")
    @TaxIdentifier(message = "O CPF ou CNPJ precisa ser válido.")
    public String taxIdentifier;

    public UserCreateDTO() {}

    public UserCreateDTO(String name, String email, String telephone, String password, String taxIdentifier) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.taxIdentifier = taxIdentifier;
    }
}
