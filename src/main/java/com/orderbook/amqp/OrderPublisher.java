package com.orderbook.amqp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.orderbook.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.UUID;

@Component
public class OrderPublisher {

    private Logger logger = LoggerFactory.getLogger(OrderPublisher.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    public void publishOrder(Order order) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        order.setTradeId(uuid);
        String orderAsString = xmlMapper.writeValueAsString(order);
        logger.debug("Publishing order : " + orderAsString);
        amqpTemplate.convertAndSend(exchange, "", orderAsString);
    }
}
