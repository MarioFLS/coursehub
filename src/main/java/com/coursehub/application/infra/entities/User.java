package com.coursehub.application.infra.entities;
import com.coursehub.application.infra.DTO.UserDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = true, unique = true, nullable = false)
    private UUID id;
    private String name;
    private String email;
    private String telephone;

    public User() {}

    public User(UUID id, String name, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    public User(UserDTO dto) {
        this.id = UUID.randomUUID();
        this.email = dto.email;
        this.name = dto.name;
        this.telephone = dto.telephone;
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
