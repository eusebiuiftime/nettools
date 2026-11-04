package dev.eusebiu.nettools.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "modem_traffic")
@Getter
@Setter
@NoArgsConstructor
public class ModemTraffic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recorded_at", nullable = false)
    private Instant recordedAt;

    @Column(name = "monthly_rx_bytes")
    private Long monthlyRxBytes;

    @Column(name = "monthly_tx_bytes")
    private Long monthlyTxBytes;

    @Column(name = "traffic_period")
    private String trafficPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modem_id_fk")
    private Modem modem;

    @PrePersist
    protected void onCreate() {
        if (recordedAt == null) {
            recordedAt = Instant.now();
        }
    }


}
