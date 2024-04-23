package com.spring_boot.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring_boot.rabbitmq.model.User;

@Service
public class RabbitMQJsonProducer {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.jsonRoutingKey.name}")
	private String jsonRoutingKey;
	
    private RabbitTemplate rabbitTemplate;
	
	private static final Logger logger= LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
		this.rabbitTemplate=rabbitTemplate;
	}
	
	public void sendJsonMessage(User user){
		logger.info(String.format("Json message sent -> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
	}
}
