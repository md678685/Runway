package io.github.md678685.runway.service;

import io.github.md678685.runway.model.User;
import io.github.md678685.runway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class RunwayUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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
