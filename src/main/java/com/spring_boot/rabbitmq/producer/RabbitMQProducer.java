package com.spring_boot.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingKey.name}")
	private String routingKey;
	
	private RabbitTemplate rabbitTemplate;
	
	private static final Logger logger= LoggerFactory.getLogger(RabbitMQProducer.class);
	
	public RabbitMQProducer(RabbitTemplate rabbitTemplate){
		this.rabbitTemplate=rabbitTemplate;
	}

   public void sendMessage(String messgae) {
	   logger.info(String.format("Message sent -> %s", messgae)) ; 
	   rabbitTemplate.convertAndSend(exchange, routingKey, messgae);
   }
   
}

