package com.spring_boot.rabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.rabbitmq.model.User;
import com.spring_boot.rabbitmq.producer.RabbitMQJsonProducer;
import com.spring_boot.rabbitmq.producer.RabbitMQProducer;

@RestController
@RequestMapping("/api")
public class MessageJsonController {

	
    private RabbitMQJsonProducer producer;
	
	private MessageJsonController(RabbitMQJsonProducer producer){	
		this.producer=producer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		producer.sendJsonMessage(user);
		return ResponseEntity.ok("Json message sent to RabbitMQ");
	}
}
