package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.Modem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModemRepository extends JpaRepository<Modem, Integer> {
}
