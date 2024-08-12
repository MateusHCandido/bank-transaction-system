package com.github.io.MateusHCandido.account_service.message;

import com.github.io.MateusHCandido.account_service.domain.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, TransactionDetails> transactionKafkaTemplate;

    private static String KAFKA_TRANSACTION_TOPIC = "transaction-topic";

    public void sendMessage(TransactionDetails transactionDetails){
        transactionKafkaTemplate.send(KAFKA_TRANSACTION_TOPIC, transactionDetails);
    }
}
