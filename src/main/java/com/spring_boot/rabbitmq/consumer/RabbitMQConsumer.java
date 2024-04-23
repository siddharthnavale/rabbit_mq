package com.spring_boot.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
	
	private static final Logger logger=LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	
	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void consume(String messgae) {
		logger.info(String.format("Received Message -> %s", messgae));
	}

			
}
