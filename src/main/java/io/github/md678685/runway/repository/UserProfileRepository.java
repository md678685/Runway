package io.github.md678685.runway.repository;

import io.github.md678685.runway.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByUsername(String name);

    UserProfile findByEmail(String email);

}
