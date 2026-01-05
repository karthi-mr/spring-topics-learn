package com.preflearn.kafka.consumer;

import com.preflearn.kafka.payload.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    // @KafkaListener(topics = "preflearn", groupId = "myGroup")
    public void consumeMessage(String message) {
      log.info("Consuming message from preflearn topic: {}", message);
    }

    @KafkaListener(topics = "preflearn", groupId = "myGroup")
    public void consumeJsonMessage(Student student) {
      log.info("Consuming Json message from preflearn topic: {}", student);
    }
}
