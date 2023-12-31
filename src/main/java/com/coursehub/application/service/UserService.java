package com.coursehub.application.service;

import com.coursehub.application.domain.DTO.AuthenticationUserDTO;
import com.coursehub.application.domain.DTO.UserCreateDTO;
import com.coursehub.application.domain.DTO.UserUpdateDTO;
import com.coursehub.application.domain.entities.User;
import com.coursehub.application.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateDTO userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    public Optional<User> updateUser(UUID id, UserUpdateDTO userDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (userDto.getName().isPresent()) {
                existingUser.setName(userDto.getName().get());
            }
            if (userDto.getTelephone() != null) {
                existingUser.setTelephone(userDto.getTelephone().get());
            }

            userRepository.save(existingUser);

            return Optional.of(existingUser);
        }

        return optionalUser;
    }


    public Optional<User> removeUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return optionalUser;
        }
        return optionalUser;
    }

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id).stream().findFirst();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> loginUser(AuthenticationUserDTO authDto) {
        Optional<User> user = userRepository.findByEmail(authDto.email());
        if(user.isPresent()) {
            boolean security = new BCryptPasswordEncoder().matches(authDto.password(), user.get().getPassword());

            if(security) return user;
        }
        return Optional.empty();
    }
}
