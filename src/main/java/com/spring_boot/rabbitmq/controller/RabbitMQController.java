package com.spring_boot.rabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.rabbitmq.producer.RabbitMQProducer;

@RestController
@RequestMapping("/api/")
public class RabbitMQController {
	
	private RabbitMQProducer producer;
	
	private RabbitMQController(RabbitMQProducer producer){	
		this.producer=producer;
	}
	
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message")String mesage){
		producer.sendMessage(mesage);
		return ResponseEntity.ok("Message sent to RabbitMQ");
	}
	

}
