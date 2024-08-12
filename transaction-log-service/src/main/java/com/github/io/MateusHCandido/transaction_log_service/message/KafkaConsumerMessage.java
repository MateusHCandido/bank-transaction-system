package com.github.io.MateusHCandido.transaction_log_service.message;

import com.github.io.MateusHCandido.transaction_log_service.entity.TransactionDetails;
import com.github.io.MateusHCandido.transaction_log_service.service.TransactionDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = (Logger) LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private TransactionDetailService transactionDetailService;

    @KafkaListener(topics = "transaction-topic", groupId = "transaction-details-group")
    public void listening(TransactionDetails transactionDetails) {
        LOG.info("Received Transaction Details");
        transactionDetailService.saveTransactionDetail(transactionDetails);
    }
}
