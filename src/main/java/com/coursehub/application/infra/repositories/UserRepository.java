package com.coursehub.application.infra.repositories;

import com.coursehub.application.infra.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
