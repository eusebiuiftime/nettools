package dev.eusebiu.nettools.service.impl;

import dev.eusebiu.nettools.service.WakeOnLanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.HexFormat;


@Service
@Slf4j
public class WakeOnLanServiceImpl implements WakeOnLanService {

    @Value("${nettools.wol.mac-address}")
    private String macAddress;

    @Value("${nettools.wol.broadcast-address}")
    private String broadcastAddress;

    @Override
    public void wake() {

        try {
            byte[] macAddressBytes = HexFormat.ofDelimiter(":").parseHex(macAddress);
            byte[] magicPacket = new byte[102];

            for (int i = 0; i < 6; i++ ) {
                magicPacket[i] = (byte) 0xFF;
            }

            for (int i = 0; i < 16; i++) {
                System.arraycopy(macAddressBytes, 0, magicPacket, 6 + i * 6, 6);
            }

            InetAddress broadcast = InetAddress.getByName(broadcastAddress);

            try (DatagramSocket socket = new DatagramSocket()) {
                socket.setBroadcast(true);

                DatagramPacket packet = new DatagramPacket(
                        magicPacket,
                        magicPacket.length,
                        broadcast,
                        9
                );

                log.info("Sending magic packet to {}", broadcastAddress);
                socket.send(packet);
                log.info("Sent magic packet to {}", broadcastAddress);

            }

        } catch (IOException e) {
            throw new IllegalStateException("Unable to send magic packet to " + broadcastAddress, e);
        }


    }
}
