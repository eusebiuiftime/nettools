package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
