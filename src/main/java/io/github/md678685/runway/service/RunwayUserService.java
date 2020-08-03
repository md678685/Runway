package io.github.md678685.runway.service;

import io.github.md678685.runway.controller.model.UserRegisterDto;
import io.github.md678685.runway.exception.UserRegisterException;
import io.github.md678685.runway.model.User;
import io.github.md678685.runway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RunwayUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RunwayUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new RunwayUserPrincipal(user);
    }

    public static class RunwayUserPrincipal implements UserDetails {

        private final User user;

        public RunwayUserPrincipal(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.add(getRole("USER"));
            if (user.isAdmin()) {
                authorities.add(getRole("ADMIN"));
            }
            if (user.isStaff()) {
                authorities.add(getRole("STAFF"));
            }
            return Set.copyOf(authorities);
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return user.isActive();
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.isActive();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return user.getPassword() != null;
        }

        @Override
        public boolean isEnabled() {
            return user.isActive();
        }

        private SimpleGrantedAuthority getRole(String name) {
            return new SimpleGrantedAuthority("ROLE_" + name);
        }
    }

}
