package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByNicknameIgnoreCase(String nickname);

    Optional<User> findByEmailIgnoreCase(String email);
}
