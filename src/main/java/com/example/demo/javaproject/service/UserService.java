package com.example.demo.javaproject.service;

import com.example.demo.javaproject.entity.User;
import com.example.demo.javaproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        log.info("Retrieving all users");
        List<User> users = userRepository.findAll();
        log.info("Retrieved {} users", users.size());
        return users;
    }

    public User findById(long id) {
        log.info("Searching for user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with id {} not found", id);
                    return new RuntimeException("User not found");
                });
        log.info("User found: {}", user);
        return user;
    }

    public User findByUsername(String username) {
        log.info("Searching for user with username: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User with username {} not found", username);
                    return new RuntimeException("User not found");
                });
        log.info("User found: {}", user);
        return user;
    }

    public User save(User user) {
        log.info("Saving user: {}", user);
        User savedUser = userRepository.save(user);
        log.info("User saved successfully: {}", savedUser);
        return savedUser;
    }

    public void deleteById(Long id) {
        log.info("Deleting username with id: {}", id);
        userRepository.deleteById(id);
        log.info("Username with id {} deleted successfully", id);
    }
}
