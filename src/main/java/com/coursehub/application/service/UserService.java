package com.coursehub.application.service;

import com.coursehub.application.infra.DTO.UserDTO;
import com.coursehub.application.infra.entities.User;
import com.coursehub.application.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    public Optional<User> updateUser(UUID id, UserDTO userDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User userFromDb = optionalUser.get();
            userFromDb.setName(userDto.name);
            userFromDb.setEmail(userDto.email);
            userFromDb.setTelephone(userDto.telephone);

            User userUpdate = userRepository.save(userFromDb);
            return Optional.of(userUpdate);
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
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
