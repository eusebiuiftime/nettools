package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
}
