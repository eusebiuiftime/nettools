package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.Modem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModemRepository extends JpaRepository<Modem, Integer> {
}
