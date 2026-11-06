package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.Role;
import dev.eusebiu.nettools.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleEnum name);
}
