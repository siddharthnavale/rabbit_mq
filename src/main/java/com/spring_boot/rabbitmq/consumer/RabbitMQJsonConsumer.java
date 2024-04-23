package com.spring_boot.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.spring_boot.rabbitmq.model.User;


@Service
public class RabbitMQJsonConsumer {
	
private static final Logger logger=LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	
	@RabbitListener(queues = {"${rabbitmq.jsonQueue.name}"})
	public void consume(User user) {
		logger.info(String.format("Received Json Message -> %s", user.toString()));
	}

}
