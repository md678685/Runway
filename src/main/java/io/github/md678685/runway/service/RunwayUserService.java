package io.github.md678685.runway.service;

import io.github.md678685.runway.controller.model.UserRegisterDto;
import io.github.md678685.runway.exception.UserRegisterException;
import io.github.md678685.runway.model.User;
import io.github.md678685.runway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RunwayUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegisterDto userDto) throws UserRegisterException {
        if (usernameExists(userDto.getUsername())) {
            throw new UserRegisterException("A user already exists with the username '" + userDto.getUsername() + "'.");
        } else if (emailExists(userDto.getEmail())) {
            throw new UserRegisterException("A user already exists with the email '" + userDto.getEmail() + "'.");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
