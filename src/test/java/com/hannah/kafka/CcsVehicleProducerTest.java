package com.hannah.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hannah.kafka.entity.CcsVehicleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@SpringBootTest
public class CcsVehicleProducerTest {

    @Autowired
    private KafkaTemplate<String, CcsVehicleMessage> ccsVehicleKafkaTemplate;

    private static final String TOPIC_NAME = "CCS-VEHICLE2";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSend() throws Exception {
        CcsVehicleMessage ccsVehicleMessage = CcsVehicleMessage.builder()
                .ccspId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .vin("PFCKP81BUPF000465")
                .ccsStartdate(LocalDateTime.of(2022, 1, 9, 0, 0, 0))
                .mdlCd("8I")
                .mdlNm("IONIQ 5")
                .mdlyCd("2023")
                .mdlyNm("2023")
                .fsc("9A22A8IW5ZHZ7Z")
                .plateNo("RZA11H")
                .extColCd("NB9")
                .extColCd("MIDNIGHT BLACK PEARL")
                .intColCd("YGN")
                .intColNm("DARK PEBBLE GRAY")
                .fuelType("7")
                .brand("H")
                .bodytype("0")
                .build();


        String message = objectMapper.writeValueAsString(ccsVehicleMessage);
        System.out.println("message = " + message);

        ccsVehicleKafkaTemplate.send(TOPIC_NAME, ccsVehicleMessage);
    }
}
