package com.hannah.kafka.consumer;


import com.hannah.kafka.entity.CcsVehicleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CcsVehicleConsumer {

    private static final String TOPIC_NAME = "CCS-VEHICLE2";
    private static final String CONSUMER_GROUP_ID = "ECS-CCS-VEHICLE";


    /**
     * consumer receive
     */
    @KafkaListener(
            containerFactory = "ccsVehicleKafkaListenerContainerFactory"
            , groupId = CONSUMER_GROUP_ID
            , topics = TOPIC_NAME                 //TOPIC명 미정(가칭) : TODO 추후 변경
            , concurrency = "3"                 //Consumer Process Thread 갯수 :
    )
    public void receive(CcsVehicleMessage ccsVehicleMessage, Acknowledgment ack) {
        log.info("received data = {}", ccsVehicleMessage);
        ack.acknowledge();  //수동commit
    }
}
