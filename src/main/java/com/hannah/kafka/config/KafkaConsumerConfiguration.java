package com.hannah.kafka.config;


import com.hannah.kafka.entity.CcsVehicleMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


/**
 * Kafka Consumer Configuration
 */
@Configuration
//@EnableKafka
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> consumerConfigs() {

        System.out.println("kafkaProperties.getBootstrapServers() = " + kafkaProperties.getBootstrapServers());

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);         //autocommit 비활성화

        //Consumer가 Topic에 처음 접속했을 때 어디서 부터 읽을 것인지 옵션, --from-beginning과 동일(earliest : 처음offset, lastest: 마지막offset)
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return props;
    }

    @Bean
    public ConsumerFactory<String, CcsVehicleMessage> ccsVehicleConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs()
                , new StringDeserializer()
                , new JsonDeserializer<>(CcsVehicleMessage.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory ccsVehicleKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CcsVehicleMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ccsVehicleConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
