package com.coursehub.application.domain.entities;

import com.coursehub.application.domain.DTO.UserCreateDTO;
import com.coursehub.application.domain.DTO.UserUpdateDTO;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = true, unique = true, nullable = false)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "tax_identifier", nullable = false, unique = true)
    private String taxIdentifier;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Transient
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(UUID id, String name, String email, String telephone, String password, String taxIdentifier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.taxIdentifier = taxIdentifier;
    }

    public User(UserCreateDTO dto) {
        this.id = UUID.randomUUID();
        this.email = dto.email;
        this.name = dto.name;
        this.telephone = dto.telephone;
        this.password = passwordEncoder.encode(dto.password);
        this.taxIdentifier = dto.taxIdentifier;
    }

    public User(UserUpdateDTO dto) {
        if (dto.name.isPresent()) this.name = dto.name.get();
        if (dto.telephone.isPresent()) this.telephone = dto.telephone.get();
    }

    public User(UUID id, String name, String email, String telephone, String taxIdentifier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.taxIdentifier = taxIdentifier;
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }

    public void setTaxIdentifier(String taxIdentifier) {
        this.taxIdentifier = taxIdentifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
