package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.Role;
import dev.eusebiu.nettools.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleEnum name);
}
