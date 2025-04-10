package dev.guilhermeluan.service;

import dev.guilhermeluan.domain.User;
import dev.guilhermeluan.exception.NotFoundException;
import dev.guilhermeluan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmailOrThrowNotFound(String email) {
        User userFound = userRepository.findByEmail(email);

        if (userFound == null) {
            throw new NotFoundException("User not found");
        }
        return userFound;
    }

}
