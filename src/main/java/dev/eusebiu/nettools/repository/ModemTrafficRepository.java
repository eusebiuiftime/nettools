package dev.eusebiu.nettools.repository;

import dev.eusebiu.nettools.entity.ModemTraffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModemTrafficRepository extends JpaRepository<ModemTraffic, Integer> {
}
